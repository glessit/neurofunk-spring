package com.glessit.neurofunky.configuration.security.beans;

import com.glessit.neurofunky.service.ITokenService;
import com.glessit.neurofunky.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FacebookAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ITokenService tokenService;
    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("Try to use token {}", authentication.getCredentials());
        Long requestToken = Long.valueOf(authentication.getCredentials().toString());

        if (tokenService.isTokenValid(requestToken)) {
            authentication.setAuthenticated(true);
            // get user from DB
//            User user = userService.getUserByFacebookId(facebookId);
//            Long token = tokenService.createTokenForUser(user, tokenService.get());

//            FacebookAuthentication facebookAuthentication = new FacebookAuthentication(String.valueOf(token));
            return authentication;
        }
        else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (FacebookAuthentication.class.isAssignableFrom(authentication));
    }
}
