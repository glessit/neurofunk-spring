package com.glessit.neurofunky.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class NewsDto {

    private Long id;
    private LocalDateTime created;
    private Boolean visible;
    private Boolean shortNews;
    private String news;
    private String title;
    private String image;
}
