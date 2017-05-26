package com.glessit.neurofunky.rest;

import com.glessit.neurofunky.rest.dto.NewsDto;
import com.glessit.neurofunky.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/news")
public class NewsResource {

    @Autowired
    private INewsService newsService;

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public Set<NewsDto> getFreshNews() {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NewsDto getNewsById(@PathVariable(value = "id") Integer newsId) {
        return newsService.getNewsById(newsId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    @PreAuthorize(value = "hasRole('glessit')")
    public NewsDto createNews(NewsDto news) {

    }

}
