package com.glessit.neurofunky.repository;

import com.glessit.neurofunky.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
