package com.book.es.service;

import com.book.es.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface BookService {

    Book add(Book book);

    Optional<Book> findById(String id);

    Iterable<Book> findByNumber(String bookNumber, Pageable pageable);

    Page<Book> search(Book book, Pageable pageable);

    boolean updateById(Book book) throws RuntimeException;

    Book findByNumberForUpdate(String bookNumber);

}
