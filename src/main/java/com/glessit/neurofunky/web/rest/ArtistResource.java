package com.glessit.neurofunky.web.rest;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.service.IArtistService;
import com.glessit.neurofunky.service.dto.ArtistTracksCountDto;
import com.glessit.neurofunky.web.rest.dto.ArtistDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/artist")
@Slf4j
public class ArtistResource {

    @Autowired
    private IArtistService artistService;
    @Autowired
    @Qualifier(value = "primaryConverterService")
    private ConversionService conversionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<ArtistDto> pageableArtist(Pageable pageable) throws IllegalAccessException {
        if (pageable.getPageSize() > 20) {
            throw new IllegalAccessException("Can't fetch more than 10 items");
        }
        Page<Artist> result = artistService.fetchArtist(pageable);
        return new PageImpl<>(
                (List<ArtistDto>) conversionService.convert(
                        result.getContent(),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Artist.class)),
                        TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ArtistDto.class))
                )
        );
    }

    @RequestMapping(value = "/tracks/count", method = RequestMethod.GET)
    public Set<ArtistTracksCountDto> getTracksCountPerArtist() {
        return artistService.getTracksCountPerArtist();
    }
}
