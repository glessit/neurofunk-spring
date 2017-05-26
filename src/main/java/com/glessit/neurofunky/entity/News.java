package com.glessit.neurofunky.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"NFK_NEWS\"")
public class News extends AbstractPersistable<Long> implements java.io.Serializable {

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Getter
    @Setter
    @Column
    private Boolean visible = true;

    @Getter
    @Setter
    @Column
    private Boolean shortNews = true;

    @Getter
    @Setter
    @Column(nullable = false)
    private String news;

    @Getter
    @Setter
    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    @Column
    private String image;

    private News() {}

    public News(String news, String title) {
        this.news = news;
        this.title = title;
    }
}
