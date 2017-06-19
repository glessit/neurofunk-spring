package com.glessit.neurofunky.service;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.service.dto.ArtistTracksCountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IArtistService {

    Page<Artist> fetchArtist(Pageable pageable);
    Set<ArtistTracksCountDto> getTracksCountPerArtist();
}
