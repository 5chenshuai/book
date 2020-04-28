package com.book.es.impl;

import com.book.es.bean.Book;
import com.book.es.bean.User;
import com.book.es.enums.BookStatusEnum;
import com.book.es.mapper.mycat.BookMapper;
import com.book.es.repository.BookDao;
import com.book.es.service.AliyunOSSService;
import com.book.es.service.BookService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AliyunOSSService aliyunOSSService;

    public Book add(Book book) {
        book.setStatus(BookStatusEnum.BOOK_ABLE.getCode());
        book.setCreatedTime(new Date());
        if(bookMapper.insertBook(book)==1) {
            System.out.println(book);
            bookRepository.save(book);
        }
        return book;
    }

    public Iterable<Book> findByNumber(String bookNumber,Pageable pageable) {
        MatchQueryBuilder bookNumberQuery = QueryBuilders.matchQuery("bookNumber", bookNumber);
        Iterable<Book> books;
        if(pageable==null) {
            books = bookRepository.search(bookNumberQuery);
        } else {
            books = bookRepository.search(bookNumberQuery,pageable);
        }
        return books;
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public Page<Book> search(Book book, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(!StringUtils.isEmpty(book.getBookName())) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("bookName", book.getBookName());
            boolQueryBuilder.must(matchQuery);
        }
        if(!StringUtils.isEmpty(book.getAuthor())) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("author", book.getAuthor());
            boolQueryBuilder.must(matchQuery);
        }
        if(!StringUtils.isEmpty(book.getPress())) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("press", book.getPress());
            boolQueryBuilder.must(matchQuery);
        }
        if(!StringUtils.isEmpty(book.getStatus())) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("status", book.getStatus());
            boolQueryBuilder.must(matchQuery);
        }
        if(!StringUtils.isEmpty(book.getBookType())) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("bookType", book.getBookType());
            boolQueryBuilder.must(matchQuery);
        }
        Page<Book> books = bookRepository.search(boolQueryBuilder, pageable);
        return books;
    }

    @Override
    public boolean updateById(Book book) throws RuntimeException {
        Book bookDB = null;
        Optional<Book> books = findById(book.getId() + "");
        if(books.isPresent()) {
            bookDB = books.get();
        } else {
            List<Book> booksDB = bookMapper.selectBookByNumber(book.getBookNumber());
            if(booksDB!=null&&booksDB.size()>0) {
                bookDB = booksDB.get(0);
            } else {
                throw new RuntimeException("书本不存在");
            }
        }
        bookToBookDB(book,bookDB);
        if(bookMapper.updateById(bookDB)==1) {
            if(book.getStatus()==BookStatusEnum.BOOK_DELETE.getCode()) {
                bookRepository.delete(bookDB);
            } else {
                bookRepository.save(bookDB);
            }
            return true;
        }
        return false;
    }

    private void bookToBookDB(Book book,Book bookDB) {
        if(book.getBookNumber()!=null) {
            bookDB.setBookNumber(book.getBookNumber());
        }
        if(book.getAuthor()!=null) {
            bookDB.setAuthor(book.getAuthor());
        }
        if(book.getPress()!=null) {
            bookDB.setPress(book.getPress());
        }
        if(book.getFloor()!=null) {
            bookDB.setFloor(book.getFloor());
        }
        if(book.getBookshelf()!=null) {
            bookDB.setBookshelf(book.getBookshelf());
        }
        if(book.getBookNumber()!=null) {
            bookDB.setBookNumber(book.getBookNumber());
        }
        if(book.getStatus()!=null) {
            bookDB.setStatus(book.getStatus());
        }
        if(book.getEdition()!=null) {
            bookDB.setEdition(book.getEdition());
        }
        if(book.getDescribe()!=null) {
            bookDB.setDescribe(book.getDescribe());
        }
        if(book.getPicture()!=null) {
            bookDB.setPicture(book.getPicture());
        }
        if(book.getBookType()!=null) {
            bookDB.setBookType(book.getBookType());
        }
    }

    @Override
    public Book findByNumberForUpdate(String bookNumber) {
        return bookMapper.findByNumberForUpdate(bookNumber);
    }



}
