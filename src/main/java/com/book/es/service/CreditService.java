package com.book.es.service;

import com.book.es.bean.Credit;
import com.book.es.web.PageResult;
import org.springframework.data.domain.Pageable;


public interface CreditService {

    boolean addCredit(Credit credit);

    PageResult<Credit> queryCredit(Integer userId, String bookNumber, String bookName,Pageable pageable);

}
