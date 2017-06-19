package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.entity.Source;
import com.glessit.neurofunky.entity.Track;
import com.glessit.neurofunky.repository.ArtistRepository;
import com.glessit.neurofunky.repository.TrackRepository;
import com.glessit.neurofunky.service.ITrackService;
import com.glessit.neurofunky.web.rest.dto.SimpleTrackDto;
import com.glessit.neurofunky.web.rest.dto.TrackDto;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TrackService implements ITrackService {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Track> fetchTrack(Pageable pageable) {
        return trackRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Track create(TrackDto trackDto) {
        List<Artist> artists = artistRepository.findAll(trackDto.getArtists());
        Track track = new Track(Sets.newHashSet(artists), trackDto.getTrack(), trackDto.getLength(), new Source(""));
        return trackRepository.save(track);
    }

    @Override
    @Transactional
    public Track create(SimpleTrackDto track) {
        String[] separated = track.getFullTrackName().split("-");
        String artists = separated[0];
        String trackName = separated[1];




        return null;
    }
}
