package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.repository.NewsRepository;
import com.glessit.neurofunky.service.INewsService;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<News> getFreshNews() {
//        newsRepository.find
        return null;
    }

    @Override
    public News create(NewsDto DTO) {
        News newsItem = new News(DTO);
        newsItem.setCreated(LocalDateTime.now());
        return newsRepository.save(newsItem);
    }
}
