package com.glessit.neurofunky.repository;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

//    @Query("select new NewsDto(f.id, f.name) from News n limit :limit sort by created DESC")
//    Collection<NewsDto> getFreshNews(Integer limit);
}
