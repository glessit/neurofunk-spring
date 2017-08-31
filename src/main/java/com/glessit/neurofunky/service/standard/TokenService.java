package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.Token;
import com.glessit.neurofunky.entity.User;
import com.glessit.neurofunky.repository.TokenRepository;
import com.glessit.neurofunky.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.time.LocalDateTime;

@Service
public class TokenService implements ITokenService {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TokenRepository tokenRepository;

    private final static String RANDOM_BY_SQL = "select floor(random() * 1000000000)";

    @PostConstruct
    public void init() {
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional(readOnly = true)
    public Long get() {
        return jdbcTemplate.queryForObject(RANDOM_BY_SQL, Long.class);
    }

    @Override
    public Long createTokenForUser(User user, Long token) {
        Token tokenEntity = new Token(token, user, LocalDateTime.now().plusDays(30));
        tokenRepository.save(tokenEntity);
        return tokenEntity.getToken();
    }

    @Override
    public boolean isTokenValid(Long requestToken) {
        Token token = tokenRepository.findOneByToken(requestToken);
        if (null != token && token.getExpireDateTime().isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByToken(String authToken) {
        return tokenRepository.findOneByToken(Long.valueOf(authToken)).getUser();
    }
}
