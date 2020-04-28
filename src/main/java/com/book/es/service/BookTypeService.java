package com.book.es.service;

import java.util.List;

public interface BookTypeService {

    int addBookType(String bookType);

    List<String> selectBookType();

}
