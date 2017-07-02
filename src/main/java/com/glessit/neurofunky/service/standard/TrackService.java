package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.entity.Source;
import com.glessit.neurofunky.entity.Track;
import com.glessit.neurofunky.repository.ArtistRepository;
import com.glessit.neurofunky.repository.TrackRepository;
import com.glessit.neurofunky.service.ITrackService;
import com.glessit.neurofunky.service.dto.FullTrackNameDto;
import com.glessit.neurofunky.web.rest.dto.ArtistDto;
import com.glessit.neurofunky.web.rest.dto.SimpleTrackDto;
import com.glessit.neurofunky.web.rest.dto.SourceDto;
import com.glessit.neurofunky.web.rest.dto.TrackDto;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrackService implements ITrackService {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Page<FullTrackNameDto> fetchTrack(Pageable pageable) {

        Set<FullTrackNameDto> pageContent = new TreeSet<>();

        Stopwatch stopwatch = Stopwatch.createStarted();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Track> criteriaQuery = criteriaBuilder.createQuery(Track.class);

        Root<Track> root = criteriaQuery.from(Track.class);
        root.join("artists");
        root.join("source");

        criteriaQuery.select(root);

        TypedQuery<Track> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Track> resultOfQuery = typedQuery.getResultList();

        return new PageImpl<>(
                resultOfQuery.stream().map(track -> {
                    Set<ArtistDto> artistDtos = track.getArtists()
                            .stream().map(artist -> new ArtistDto(artist.getId(), artist.getName(), ""))
                            .collect
                                    (Collectors.toSet
                                            ());

                    return new FullTrackNameDto(
                            artistDtos, track.getTrack(), track.getLength(), new SourceDto(track.getSource().getYoutube(),
                            track
                            .getSource()
                            .getDescription
                                    ()));

                })
                        .collect(Collectors.toList()));
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
