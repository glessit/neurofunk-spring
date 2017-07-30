package com.glessit.neurofunky.repository;

import com.glessit.neurofunky.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Token findOneByToken(Long token);
}
