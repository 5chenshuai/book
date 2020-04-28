package com.book.es.impl;

import com.book.es.mapper.bookManger.BookTypeMapper;
import com.book.es.service.BookService;
import com.book.es.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Override
    public int addBookType(String bookType) {
        return bookTypeMapper.addBookType(bookType);
    }

    @Override
    public List<String> selectBookType() {
        return bookTypeMapper.selectBookType();
    }
}
