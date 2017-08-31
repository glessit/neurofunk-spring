package com.glessit.neurofunky.service;


import com.glessit.neurofunky.entity.User;
import com.glessit.neurofunky.service.dto.FacebookToken;

public interface ISecurityService {
    /**
     * Do auto-login
     * @param id fbID for auth
     * @return
     */
    FacebookToken login(Long id);

    /**
     * Get currently authenticated user
     * It user current http request
     * @return User
     */
    User getAuthenticatedUser();
}
