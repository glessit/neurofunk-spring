package com.glessit.neurofunky.web.rest;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.entity.Track;
import com.glessit.neurofunky.service.ITrackService;
import com.glessit.neurofunky.web.rest.dto.ArtistDto;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import com.glessit.neurofunky.web.rest.dto.SimpleTrackDto;
import com.glessit.neurofunky.web.rest.dto.TrackDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/track")
@Slf4j
public class TrackResource {

    @Autowired
    @Qualifier(value = "primaryConverterService")
    private ConversionService conversionService;
    @Autowired
    private ITrackService trackService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<TrackDto> pageableTracks(Pageable pageable) throws IllegalAccessException {
        if (pageable.getPageSize() > 20) {
            throw new IllegalAccessException("Can't fetch more than 10 items");
        }
        Page<Track> result = trackService.fetchTrack(pageable);
        return new PageImpl<>(
                (List<TrackDto>) conversionService.convert(
                        result.getContent(),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Track.class)),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TrackDto.class))
                )
        );
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    @PreAuthorize(value = "hasRole('glessit')")
    public TrackDto createTrack(@RequestBody TrackDto track) {
        log.info("Create track. Name: {}", track.getTrack());
        return conversionService.convert(trackService.create(track), TrackDto.class);
    }

    @RequestMapping(value = "/create/youtube", method = RequestMethod.GET)
    public TrackDto createYoutubeTrack() {
        return conversionService.convert(trackService.create(new SimpleTrackDto("simepletrackname", 123123l,
                "httpurl")),
                TrackDto
                .class);
    }
}
