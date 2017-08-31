package com.glessit.neurofunky.web.rest.dto;

import com.glessit.neurofunky.entity.News;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class NewsDto {

    @Getter
    @Setter
    private Long id;

    @Getter
    private LocalDateTime created = LocalDateTime.now();

    @Setter
    private Boolean visible;

    public Boolean isVisible() {
        return visible;
    }

    @Setter
    private Boolean shortNews;

    public Boolean isShortNews() {
        return shortNews;
    }

    @Getter
    @Setter
    private String news;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String image;

    public NewsDto() {
    }

    public final static NewsDto create() {
        return new NewsDto();
    }

    public NewsDto(News news) {

        NewsDto newsItem = create();
        newsItem.setId(news.getId());
//        newsItem.setCreated(news.getCreated());
        newsItem.setVisible(news.isVisible());
        newsItem.setShortNews(news.isShortNews());
        newsItem.setNews(news.getNews());
        newsItem.setTitle(news.getTitle());
        newsItem.setImage(news.getImage());
//        return news;
    }
}
