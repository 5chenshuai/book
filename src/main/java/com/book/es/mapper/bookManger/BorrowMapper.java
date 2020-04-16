package com.book.es.mapper.bookManger;

import com.book.es.bean.Book;
import com.book.es.bean.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

@Mapper
public interface BorrowMapper {

    int addBorrow(Borrow borrow);

    List<Borrow> queryBorrow(@Param("userId") Integer userId, @Param("bookNumber") String bookNumber, @Param("bookName") String bookName
            , @Param("status") List<Integer> status);

    int updateBorrowById(Integer id, Date examineDay, Date returnDay,Integer status);

    int updateBorrowByBookNumber(String bookNumber,Integer beforeStatus,Integer afterStatus);

    Borrow queryBorrowForUpdate(Integer id);

}
