package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.Artist;
import com.glessit.neurofunky.entity.Track;
import com.glessit.neurofunky.repository.ArtistRepository;
import com.glessit.neurofunky.service.IArtistService;
import com.glessit.neurofunky.service.dto.ArtistTracksCountDto;
import com.glessit.neurofunky.web.rest.dto.ArtistDto;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArtistService implements IArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final static int ARTIST_TRACKS_COUNT_LIMIT = 10;

    @Override
    @Transactional(readOnly = true)
    public Page<Artist> fetchArtist(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ArtistTracksCountDto> getTracksCountPerArtist() {

        Stopwatch stopwatch = Stopwatch.createStarted();

        // todo: is it possible order by criteria (order by count) ?
        Set<ArtistTracksCountDto> result = new TreeSet<>(
                (Comparator<ArtistTracksCountDto>) (o1, o2) -> o2.getTracks().compareTo(o1.getTracks())
        );

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Join<Track, Artist> join = criteriaQuery.from(Track.class).join("artists");
        criteriaQuery
            .select(
                criteriaBuilder.array(
                        criteriaBuilder.count(join.get("id")),
                        join.get("name"),
                        join.get("id")
                )
            )
            .groupBy(join.get("id"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setMaxResults(ARTIST_TRACKS_COUNT_LIMIT);

        List<Object[]> resultOfQuery = typedQuery.getResultList();
        if (result.isEmpty()) {
            result.addAll(
                    resultOfQuery
                    .stream()
                    .map(obj -> new ArtistTracksCountDto(
                            new ArtistDto(
                                    (Long) obj[2],
                                    (String) obj[1],
                                    ""
                            ),
                            ((Long) obj[0]).intValue())
                    )
                    .collect(Collectors.toSet())
            );
        }

        stopwatch.stop();
        log.info("Time execution of [getTracksCountPerArtist] is {} milliseconds.",
                stopwatch.elapsed(TimeUnit.MILLISECONDS));

        return result;
    }
}
