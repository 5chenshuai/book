package com.book.es.impl;

import com.book.es.bean.Credit;
import com.book.es.mapper.bookManger.CreditMapper;
import com.book.es.service.CreditService;
import com.book.es.web.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditMapper creditMapper;

    @Override
    public boolean addCredit(Credit credit) {
        return creditMapper.addCredit(credit)==1;
    }

    @Override
    public PageResult<Credit> queryCredit(Integer userId, String bookNumber, String bookName, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        com.github.pagehelper.Page<Credit> borrows = PageHelper.startPage(pageNumber, pageSize);
        List<Credit> temp = creditMapper.queryCredit(userId, bookNumber, bookName);
        PageInfo<Credit> borrowPageInfo = new PageInfo<>(borrows);
        long totalElements = borrowPageInfo.getTotal();//总数据条数
        int number = borrowPageInfo.getPageNum();//当前页
        int size = borrowPageInfo.getPageSize();//当前页数据条数
        int totalPages = borrowPageInfo.getPages();//总页数
        boolean first = borrowPageInfo.isIsFirstPage();
        boolean last = borrowPageInfo.isIsLastPage();
        List<Credit> content = borrowPageInfo.getList();//结果集8
        return new PageResult(content,totalPages,totalElements,number,size,first,last);
    }
}
