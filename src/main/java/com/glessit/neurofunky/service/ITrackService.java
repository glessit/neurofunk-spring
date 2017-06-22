package com.glessit.neurofunky.service;


import com.glessit.neurofunky.entity.Track;
import com.glessit.neurofunky.service.dto.FullTrackNameDto;
import com.glessit.neurofunky.web.rest.dto.SimpleTrackDto;
import com.glessit.neurofunky.web.rest.dto.TrackDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITrackService {
    Page<FullTrackNameDto> fetchTrack(Pageable pageable);
    Track create(TrackDto trackDto);
    Track create(SimpleTrackDto track);
}
