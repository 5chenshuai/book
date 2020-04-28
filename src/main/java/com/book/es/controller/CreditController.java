package com.book.es.controller;

import com.book.es.bean.Credit;
import com.book.es.service.CreditService;
import com.book.es.web.BaseController;
import com.book.es.web.PageResult;
import com.book.es.web.WebResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/credit")
public class CreditController implements BaseController {

    @Autowired
    private CreditService creditService;

    @RequiresPermissions("borrow/adminFind")
    @GetMapping("/find")
    WebResponse queryBorrow(Integer userId, String bookNumber, String bookName,@PageableDefault(page = 0,size = 10) Pageable pageable) {
        PageResult<Credit> borrows = creditService.queryCredit(userId,bookNumber,bookName,pageable);
        return ok().setData(borrows);
    }

}
