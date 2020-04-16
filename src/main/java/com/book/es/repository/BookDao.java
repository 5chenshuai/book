package com.book.es.repository;

import com.book.es.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends ElasticsearchRepository<Book,String> {
}
