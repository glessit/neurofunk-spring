package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.repository.NewsRepository;
import com.glessit.neurofunky.service.INewsService;
import com.glessit.neurofunky.service.exception.NotFoundException;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News create(NewsDto DTO) {
        News newsItem = new News(DTO);
        newsItem.setCreated(LocalDateTime.now());
        News entity = newsRepository.save(newsItem);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<News> fetchNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public News getNewsById(Long id) throws NotFoundException {
        News news = newsRepository.findOne(id);
        if (null == news) throw new NotFoundException(format("News with id [%s] not found!", id));
        return news;
    }

    @Override
    public News save(News item) {
        return newsRepository.save(item);
    }
}
