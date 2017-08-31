package com.glessit.neurofunky.repository;

import com.glessit.neurofunky.entity.LikeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeTypeRepository extends JpaRepository<LikeType, Long> {
    LikeType findOneByType(String type);
}
