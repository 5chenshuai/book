package com.book.es.impl;

import com.book.es.bean.Book;
import com.book.es.bean.Borrow;
import com.book.es.bean.User;
import com.book.es.enums.BookStatusEnum;
import com.book.es.enums.BorrowStatusEnum;
import com.book.es.mapper.bookManger.BorrowMapper;
import com.book.es.service.BookService;
import com.book.es.service.BorrowService;
import com.book.es.web.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookService bookService;

    @Override
    public boolean addBorrow(Borrow borrow) throws Exception {
        ArrayList<Integer> status = new ArrayList<>();
        status.add(BorrowStatusEnum.BORROWING_CREATED.getCode());
        status.add(BorrowStatusEnum.BORROWING_ABLE.getCode());
        List<Borrow> borrows = borrowMapper.queryBorrow(borrow.getUserId(), null, null,status);
        if(borrows==null || borrows.size()<=3) {
            for(Borrow temp:borrows) {
                if(temp.getBookNumber().equalsIgnoreCase(borrow.getBookNumber())) {
                    throw new Exception("已申请借阅此图书，请勿重复申请");
                }
            }
            return borrowMapper.addBorrow(borrow)==1;
        } else {
            throw new Exception("每人只能最多同时借3本书");
        }
    }

    @Override
    public PageResult<Borrow> queryBorrow(Integer userId, String bookNumber, String bookName, List<Integer> status, Pageable pageable) {
        if(status==null || status.size()==0) {
            status=null;
        }
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        com.github.pagehelper.Page<Borrow> borrows = PageHelper.startPage(pageNumber, pageSize);
        List<Borrow> temp = borrowMapper.queryBorrow(userId, bookNumber, bookName, status);
        PageInfo<Borrow> borrowPageInfo = new PageInfo<>(borrows);
        long totalElements = borrowPageInfo.getTotal();//总数据条数
        int number = borrowPageInfo.getPageNum();//当前页
        int size = borrowPageInfo.getPageSize();//当前页数据条数
        int totalPages = borrowPageInfo.getPages();//总页数
        boolean first = borrowPageInfo.isIsFirstPage();
        boolean last = borrowPageInfo.isIsLastPage();
        List<Borrow> content = borrowPageInfo.getList();//结果集8

        System.out.println(borrowPageInfo);
        return new PageResult(content,totalPages,totalElements,number,size,first,last);
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updateBorrowById(Integer id,Integer status) throws RuntimeException {
        Date examineDay=null;
        Date returnDay=null;
        boolean needUpdateBook = false;
        if(status == BorrowStatusEnum.BORROWING_ABLE.getCode()
            || status == BorrowStatusEnum.BORROWING_UNABLE.getCode()) {
            examineDay=new Date();
        } else if(status == BorrowStatusEnum.BORROWING_RETURN.getCode()
            || status == BorrowStatusEnum.BORROWING_CANCEL.getCode()) {
            returnDay=new Date();
        }
        Borrow borrow = borrowMapper.queryBorrowForUpdate(id);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(!user.getId().equals(borrow.getUserId())) {
            throw new RuntimeException("该用户不存在此图书");
        }
        if(borrow.getStatus()!=status) {
            Book book = bookService.findByNumberForUpdate(borrow.getBookNumber());
            if(status==BorrowStatusEnum.BORROWING_ABLE.getCode()) {//批准
                if(borrow.getStatus()!=BorrowStatusEnum.BORROWING_CREATED.getCode()) {
                    throw new RuntimeException("状态不正确");
                }
                if(book.getStatus()==BookStatusEnum.BOOK_ABLE.getCode()) {
                    book.setStatus(BookStatusEnum.BOOK_UNABLE.getCode());
                    needUpdateBook=true;
                    borrowMapper.updateBorrowByBookNumber(borrow.getBookNumber(),BorrowStatusEnum.BORROWING_CREATED.getCode(),BorrowStatusEnum.BORROWING_WAIT.getCode());
//                    throw new RuntimeException("该图书不可借");
                } else {
                    throw new RuntimeException("该图书不可借");
                }
            } else if(status==BorrowStatusEnum.BORROWING_RETURN.getCode()) {//归还
                if(borrow.getStatus()!=BorrowStatusEnum.BORROWING_ABLE.getCode()) {
                    throw new RuntimeException("状态不正确");
                }
                if(book.getStatus()==BookStatusEnum.BOOK_UNABLE.getCode()) {
                    book.setStatus(BookStatusEnum.BOOK_ABLE.getCode());
                    needUpdateBook=true;
                    borrowMapper.updateBorrowByBookNumber(borrow.getBookNumber(),BorrowStatusEnum.BORROWING_WAIT.getCode(),BorrowStatusEnum.BORROWING_CREATED.getCode());
                }
            } else if(status==BorrowStatusEnum.BORROWING_UNABLE.getCode()||status==BorrowStatusEnum.BORROWING_CANCEL.getCode()) {//回绝，撤销
                if(borrow.getStatus()!=BorrowStatusEnum.BORROWING_CREATED.getCode() && borrow.getStatus()!=BorrowStatusEnum.BORROWING_WAIT.getCode()) {
                    throw new RuntimeException("状态不正确");
                }
            }
            int i = borrowMapper.updateBorrowById(id, examineDay, returnDay, status);
            if(needUpdateBook) {
                bookService.updateById(book,null);
            }
            return i>0;
        } else {
            throw new RuntimeException("该图书处于当前状态");
        }
    }
}
