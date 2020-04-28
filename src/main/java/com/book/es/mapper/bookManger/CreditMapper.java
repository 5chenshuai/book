package com.book.es.mapper.bookManger;

import com.book.es.bean.Credit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CreditMapper {

    int addCredit(Credit credit);

    List<Credit> queryCredit(@Param("userId") Integer userId, @Param("bookNumber") String bookNumber, @Param("bookName") String bookName);

}
