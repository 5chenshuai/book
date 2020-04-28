package com.book.es.service;

import com.book.es.bean.Borrow;
import com.book.es.bean.BorrowUser;
import com.book.es.web.PageResult;
import com.github.pagehelper.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

public interface BorrowService {

    boolean addBorrow(Borrow borrow) throws Exception;

    PageResult<Borrow> queryBorrow(Integer userId, String bookNumber, String bookName, List<Integer> status, Pageable pageable);

    boolean updateBorrowById(Integer id,Integer status) throws Exception;

    List<BorrowUser> queryShouldReturnBorrow();

    boolean updateBorrowOverdue();


}
