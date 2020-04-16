package com.book.es.controller;

import com.book.es.annotation.ValidatorAnnotation;
import com.book.es.bean.Book;
import com.book.es.bean.Borrow;
import com.book.es.bean.User;
import com.book.es.enums.BorrowStatusEnum;
import com.book.es.mapper.bookManger.BorrowMapper;
import com.book.es.service.BookService;
import com.book.es.service.BorrowService;
import com.book.es.vo.BorrowVO;
import com.book.es.web.BaseController;
import com.book.es.web.PageResult;
import com.book.es.web.WebResponse;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrow")
public class BorrowController implements BaseController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookService bookService;

    @RequiresPermissions("borrow/add")
    @PostMapping("/add")
    @ValidatorAnnotation
    WebResponse addBorrow(@Valid Borrow borrow, BindingResult bindingResult) {
        Iterable<Book> books = bookService.findByNumber(borrow.getBookNumber(),null);
        //前端控制
        if (!books.iterator().hasNext()) {
            return defaultErr().setMsg("书本不存在");
        }
        Book next = books.iterator().next();
        if(next.getBookName().equalsIgnoreCase(borrow.getBookNumber())) {
            return defaultErr().setMsg("输入书名与编号不符");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        borrow.setUserId(user.getId());
        borrow.setStatus(BorrowStatusEnum.BORROWING_CREATED.getCode());
        borrow.setApplyDay(new Date());
        try {
            if(borrowService.addBorrow(borrow)) {
                return ok();
            }else {
                return defaultErr();
            }
        } catch (Exception e) {
            return defaultErr().setMsg(e.getMessage());
        }
    }

    //管理员权限
    @RequiresPermissions("borrow/adminFind")
    @GetMapping("/find")
    WebResponse queryBorrow(Integer userId,String bookNumber,String bookName,Integer status,@PageableDefault(page = 0,size = 10) Pageable pageable) {
        List<Integer> temp = new ArrayList<>();
        if(status!=null) {
            temp.add(status);
        }
        PageResult<Borrow> borrows = borrowService.queryBorrow(userId,bookNumber,bookName,temp,pageable);
//        List<BorrowVO> collect = borrows.getContent().stream().map(new Function<Borrow, BorrowVO>() {
//            @Override
//            public BorrowVO apply(Borrow borrow) {
//                BorrowVO borrowVO = new BorrowVO(borrow);
//                return borrowVO;
//            }
//        }).collect(Collectors.toList());
//
//        PageResult<BorrowVO> borrowVOPageResult = new PageResult<BorrowVO>();
//        borrowVOPageResult.setContent(collect);

        return ok().setData(borrows);
    }

    //普通用户权限
    @RequiresPermissions("borrow/find")
    @GetMapping("/findByToken")
    WebResponse queryBorrowByToken(String bookNumber,String bookName,Integer status,@PageableDefault(page = 0,size = 10) Pageable pageable) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Integer> temp = new ArrayList<>();
        if(status!=null) {
            temp.add(status);
        }
        PageResult<Borrow> borrows = borrowService.queryBorrow(user.getId(),bookNumber,bookName,temp,pageable);

//        List<BorrowVO> collect = borrows.getContent().stream().map(new Function<Borrow, BorrowVO>() {
//            @Override
//            public BorrowVO apply(Borrow borrow) {
//                BorrowVO borrowVO = new BorrowVO(borrow);
//                return borrowVO;
//            }
//        }).collect(Collectors.toList());
//
//        PageResult<BorrowVO> borrowVOPageResult = new PageResult<BorrowVO>();
//        borrowVOPageResult.setContent(collect);

        return ok().setData(borrows);
    }

    @RequiresPermissions("borrow/update")
    @PostMapping("/update")
    WebResponse updateBorrowById(Integer id,Integer status) {
        try {
            if(borrowService.updateBorrowById(id,status)) {
                return ok();
            } else {
                return defaultErr();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return defaultErr().setMsg(e.getMessage());
        }
    }
    @RequiresPermissions("borrow/cancel")
    @PostMapping("/cancel")
    WebResponse cancelBorrow(Integer id) {
        try {
            if(borrowService.updateBorrowById(id,BorrowStatusEnum.BORROWING_CANCEL.getCode())) {
                return ok();
            } else {
                return defaultErr();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return defaultErr().setMsg(e.getMessage());
        }
    }
}
