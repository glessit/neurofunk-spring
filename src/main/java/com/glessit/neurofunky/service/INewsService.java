package com.glessit.neurofunky.service;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.service.exception.NotFoundException;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsService {

    News create(NewsDto news);
    Page<News> fetchNews(Pageable pageable);
    News getNewsById(Long id) throws NotFoundException;
}
