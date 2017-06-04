package com.glessit.neurofunky.service;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.web.rest.dto.NewsDto;

import java.util.Set;

public interface INewsService {

    public Set<News> getFreshNews();

    News create(NewsDto news);
}
