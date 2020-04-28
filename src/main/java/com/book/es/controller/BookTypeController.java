package com.book.es.controller;

import com.book.es.bean.BookType;
import com.book.es.service.BookTypeService;
import com.book.es.web.BaseController;
import com.book.es.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookTypeController implements BaseController {

    @Autowired
    private BookTypeService bookTypeService;

    @PostMapping("/bookType/add")
    public WebResponse addBookType(@RequestBody BookType bookType) {
        return bookTypeService.addBookType(bookType.getBookType())==1?ok():defaultErr();
    }

    @GetMapping("/guest/bookType/get")
    public WebResponse selectBookType() {
        return ok().setData(bookTypeService.selectBookType());
    }

}
