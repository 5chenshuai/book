package com.book.es.impl;

import com.book.es.bean.Book;
import com.book.es.bean.Thesis;
import com.book.es.mapper.bookManger.ThesisMapper;
import com.book.es.repository.ThesisDao;
import com.book.es.service.ThesisService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private ThesisDao thesisDao;

    @Autowired
    private ThesisMapper thesisMapper;

    @Override
    public boolean addThesis(Thesis thesis) {
        thesis.setId(System.currentTimeMillis()+"");
        if(thesisMapper.addThesis(thesis.getId(),thesis.getThesisTitle(),thesis.getAuthor().toString()
                ,thesis.getThesisKeyword().toString(),thesis.getPdf())==1) {
            thesisDao.save(thesis);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteThesis(Thesis thesis) {
        if(thesisMapper.deleteThesis(thesis.getId())==1) {
            thesisDao.delete(thesis);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Thesis> queryThesis(Thesis thesis, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(!StringUtils.isEmpty(thesis.getThesisTitle())) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("thesisTitle", thesis.getThesisTitle());
            boolQueryBuilder.must(matchQuery);
        }
        if(thesis.getAuthor()!=null && thesis.getAuthor().size()>0) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("author", thesis.getAuthor());
            boolQueryBuilder.must(matchQuery);
        }
        if(thesis.getThesisKeyword()!=null && thesis.getThesisKeyword().size()>0) {
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("thesisKeyword", thesis.getThesisKeyword());
            boolQueryBuilder.must(matchQuery);
        }
        Page<Thesis> theses = thesisDao.search(boolQueryBuilder, pageable);
        return theses;
    }
}
