package com.book.es.service;

import com.book.es.bean.Thesis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThesisService {

    boolean addThesis(Thesis thesis);

    boolean deleteThesis(Thesis thesis);

    Page<Thesis> queryThesis(Thesis thesis, Pageable pageable);

}
