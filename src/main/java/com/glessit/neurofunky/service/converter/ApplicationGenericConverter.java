package com.glessit.neurofunky.service.converter;

import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import com.google.common.collect.Sets;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ApplicationGenericConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Sets.newHashSet(
                new ConvertiblePair(News.class, NewsDto.class)
        );
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source.getClass().equals(News.class) && targetType.getType().equals(NewsDto.class)) {
            NewsDto dto = new NewsDto();
            News source2 = (News) source;
            dto.setId(source2.getId());
            dto.setCreated(source2.getCreated());
            dto.setVisible(source2.isVisible());
            dto.setShortNews(source2.isShortNews());
            dto.setNews(source2.getNews());
            dto.setTitle(source2.getTitle());
            dto.setImage(source2.getImage());
            return dto;
        }
        return null;
    }
}
