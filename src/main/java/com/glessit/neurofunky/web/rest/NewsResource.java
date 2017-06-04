package com.glessit.neurofunky.web.rest;

import com.glessit.neurofunky.service.INewsService;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping(value = "/news")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NewsResource {

    private final static Logger LOG = LoggerFactory.getLogger(NewsResource.class);

    @Autowired
    private INewsService newsService;
    @Autowired
    @Qualifier(value = "primaryConverterService")
    private ConversionService conversionService;

    @RequestMapping(value = "/fresh", method = RequestMethod.GET)
    public Set<NewsDto> getFreshNews() {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NewsDto getNewsById(@PathVariable(value = "id") Integer newsId) {
        return null;
//        return newsService.getNewsById(newsId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    @PreAuthorize(value = "hasRole('glessi8t')")
    public NewsDto createNews(@RequestBody NewsDto news,Principal principal) {
        LOG.info("Create news. Title: {}", news.getTitle());
        return conversionService.convert(newsService.create(news), NewsDto.class);
    }

}
