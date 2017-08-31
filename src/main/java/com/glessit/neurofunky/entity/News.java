package com.glessit.neurofunky.entity;

import com.glessit.neurofunky.web.rest.dto.NewsDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"NFK_NEWS\"")
public class News extends AbstractPersistable<Long> implements Serializable, Likeable {

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Setter
    @Column
    private Boolean visible = true;

    public Boolean isVisible() {
        return visible;
    }

    @Setter
    @Column
    private Boolean shortNews = true;

    public Boolean isShortNews() {
        return shortNews;
    }

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

    @Getter
    @OneToMany
    @JoinColumn(name="NEWS_ID")
    private Set<Like> likes = new HashSet<>();

    public void addLike(Like like) {
        likes.add(like);
    }

    private News() {}

    public News(String news, String title) {
        this.news = news;
        this.title = title;
    }

    public News(NewsDto item) {
        this.created = item.getCreated();
        this.visible = item.isVisible();
        this.shortNews = item.isShortNews();
        this.news = item.getNews();
        this.title = item.getTitle();
        this.image = item.getImage();
    }

    public final static News create() {
        return new News();
    }

    @Override
    public Boolean isUserHasLike(Long userId) {
        return Boolean.valueOf(
                this.getLikes().stream().filter(like -> like.getUser().getId() == userId).findFirst().isPresent()
        );
    }
}
