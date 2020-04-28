package com.book.es.mapper.bookManger;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookTypeMapper {

    int addBookType(String bookType);

    List<String> selectBookType();

}
