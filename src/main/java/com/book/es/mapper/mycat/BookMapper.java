package com.book.es.mapper.mycat;

import com.book.es.bean.Book;
import com.book.es.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    Integer insertBook(Book book);

    List<Book> selectBookByNumber(String bookNumber);

    Integer updateById(Book book);

    Book findByNumberForUpdate(String bookNumber);

}
