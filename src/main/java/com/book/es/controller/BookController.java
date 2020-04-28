package com.book.es.controller;

import com.book.es.annotation.ValidatorAnnotation;
import com.book.es.bean.Book;
import com.book.es.bean.Borrow;
import com.book.es.enums.BookStatusEnum;
import com.book.es.mapper.mycat.BookMapper;
import com.book.es.service.AliyunOSSService;
import com.book.es.service.BookService;
import com.book.es.vo.BorrowVO;
import com.book.es.web.BaseController;
import com.book.es.web.PageResult;
import com.book.es.web.WebResponse;
import com.google.inject.internal.util.$Nullable;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/book")
public class BookController implements BaseController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AliyunOSSService aliyunOSSService;

    @Autowired
    private BookMapper bookMapper;

    @RequiresPermissions("book/add")
    @PostMapping("/add")
    @ValidatorAnnotation
    public WebResponse add(@RequestBody @Valid Book book, BindingResult bindingResult) {
        try {

            List<Book> books = bookMapper.selectBookByNumber(book.getBookNumber());
            if(books!=null && books.size()>0) {
                if(books.get(0).getStatus()==BookStatusEnum.BOOK_DELETE.getCode()) {
                    book.setStatus(BookStatusEnum.BOOK_ABLE.getCode());
                    book.setId(books.get(0).getId());
                    book.setCreatedTime(new Date());
                    updateById(book);
                    return ok().setData(book);
                } else {
                    return defaultErr().setMsg("书本已存在");
                }
            }
            Book add = bookService.add(book);
            return ok().setData(add);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return defaultErr().setMsg(e.getMessage());
        }
    }

    @RequiresPermissions("book/add")
    @PostMapping("/uploadImg")
    public WebResponse uploadImg( @RequestParam(value = "file",required = false) MultipartFile file) {
         return ok().setData(aliyunOSSService.uploadImg(file));
    }

//    @RequiresPermissions("book/find")
//    @GetMapping("/findByNumber")
    public WebResponse findByNumber(String bookNumber,Pageable pageable) {
        Iterable<Book> books = bookService.findByNumber(bookNumber,pageable);
        ArrayList<Book> temp = new ArrayList<>();
        if(books.iterator().hasNext()) {
            Book next = books.iterator().next();
            temp.add(next);
            return ok().setData(new PageResult<Book>(temp,1,Long.valueOf(1),10,0,true,true));
        } else {
            return ok().setData(new PageResult<Book>(temp,0,Long.valueOf(0),10,0,true,true));
        }
    }

    @RequiresPermissions("book/find")
    @GetMapping("/findById")
    public WebResponse findById(String id) {
        Book book = bookService.findById(id).get();
        return ok().setData(book);
    }


//    @RequiresPermissions("book/find")
    @GetMapping("/guest/search")
    public WebResponse search(Book book, @PageableDefault(page = 0,size = 10,sort = "createdTime",direction = Sort.Direction.DESC) Pageable pageable) {
        if(StringUtils.isNotEmpty(book.getBookNumber())) {
            return findByNumber(book.getBookNumber(), pageable);
        } else {
            Page<Book> search = bookService.search(book, pageable);
            return ok().setData(new PageResult<Book>(search.getContent(),search.getTotalPages(),search.getTotalElements()
                    ,search.getNumber(),search.getSize(),search.isFirst(),search.isLast()));
        }
    }

    @RequiresPermissions("book/update")
    @PostMapping("/update")
    public WebResponse updateById(@RequestBody Book book) {
        if(book.getStatus()!= BookStatusEnum.BOOK_ABLE.getCode() && book.getStatus()!= BookStatusEnum.BOOK_UNABLE.getCode() && book.getStatus()!= BookStatusEnum.BOOK_DELETE.getCode()) {
            return defaultErr().setMsg("状态不正确");
        }
        try {
            if(bookService.updateById(book)) {
                return ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return defaultErr().setMsg(e.getMessage());
        }
        return defaultErr();
    }
}
