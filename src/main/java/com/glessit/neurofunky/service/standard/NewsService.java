package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.repository.NewsRepository;
import com.glessit.neurofunky.service.INewsService;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class NewsService implements INewsService {

    private final static Integer FRESH_NEWS = 10;

    @Autowired
    private NewsRepository newsRepository;


    @Override
    @Transactional(readOnly = true)
    public Set<News> getFreshNews() {
        return null; //newsRepository.getFreshNews(FRESH_NEWS);
    }

    @Override
    public News create(NewsDto DTO) {
        News newsItem = new News(DTO);
        newsItem.setCreated(LocalDateTime.now());
        News entity = newsRepository.save(newsItem);
        return entity;
    }
}
