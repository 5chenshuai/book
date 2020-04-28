package com.book.es.mapper.bookManger;

import com.book.es.bean.Thesis;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThesisMapper {

    Integer addThesis(String id,String thesisTitle, String author,String thesisKeyword,String pdf);

    int deleteThesis(String thesisId);

}
