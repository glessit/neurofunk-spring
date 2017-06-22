package com.glessit.neurofunky.service.converter;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.entity.News;
import com.glessit.neurofunky.entity.Track;
import com.glessit.neurofunky.web.rest.dto.ArtistDto;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import com.glessit.neurofunky.web.rest.dto.TrackDto;
import com.google.common.collect.Sets;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationGenericConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Sets.newHashSet(
                new ConvertiblePair(News.class, NewsDto.class),
                new ConvertiblePair(Artist.class, ArtistDto.class),
                new ConvertiblePair(Track.class, TrackDto.class)
        );
    }

    @Override
    @Transactional
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

        if (source.getClass().equals(Artist.class) && targetType.getType().equals(ArtistDto.class)) {
          /*  Artist sourceObject = (Artist) source;
            return ArtistDto.builder()
                    .id(sourceObject.getId())
                    .name(sourceObject.getName())
                    .build();*/
        }

        if (source.getClass().equals(Track.class) && targetType.getType().equals(TrackDto.class)) {
            // todo: resolve LazyInitializationException on artists property
            Track sourceObject = (Track) source;
            return TrackDto.builder()
                    .artists(sourceObject.getArtists().stream().map(artist -> artist.getId()).collect(Collectors.toSet()))
                    .track(sourceObject.getTrack())
                    .length(sourceObject.getLength())
                    .build();
        }

        return null;
    }
}
