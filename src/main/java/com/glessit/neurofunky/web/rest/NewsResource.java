package com.glessit.neurofunky.web.rest;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.service.INewsService;
import com.glessit.neurofunky.service.exception.NotFoundException;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/news")
@Slf4j
public class NewsResource {

    @Autowired
    private INewsService newsService;
    @Autowired
    @Qualifier(value = "primaryConverterService")
    private ConversionService conversionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<NewsDto> pageableNews(Pageable pageable) throws IllegalAccessException {
        if (pageable.getPageSize() > 10) {
            throw new IllegalAccessException("Can't fetch more than 10 items");
        }
        Page<News> result = newsService.fetchNews(pageable);
        return new PageImpl<>(
                (List<NewsDto>) conversionService.convert(
                        result.getContent(),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(News.class)),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(NewsDto.class))
                )
        );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NewsDto getNewsById(@PathVariable(value = "id") Long newsId) throws NotFoundException {
        return conversionService.convert(newsService.getNewsById(newsId), NewsDto.class);
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize(value = "hasRole('glessit')")
    public NewsDto createNews(@RequestBody NewsDto news) {
        log.info("Create news. Title: {}", news.getTitle());
        return conversionService.convert(newsService.create(news), NewsDto.class);
    }

}
