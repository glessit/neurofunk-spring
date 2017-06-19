package com.glessit.neurofunky.repository;

import com.glessit.neurofunky.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track,Long> {
}
