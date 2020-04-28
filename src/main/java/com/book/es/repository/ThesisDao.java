package com.book.es.repository;

import com.book.es.bean.Thesis;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisDao extends ElasticsearchRepository<Thesis,String> {
}
