package com.glessit.neurofunky.service;

import com.glessit.neurofunky.entity.User;

public interface ITokenService {

    /**
     * Just generate simple token
     * @return token
     */
    Long get();

    /**
     * Create token for user
     * @param user
     * @return token for user
     */
    Long createTokenForUser(User user, Long token);

    /**
     * Check that token is exist in storage and hasn't yet expired
     * @param requestToken
     */
    boolean isTokenValid(Long requestToken);
}
