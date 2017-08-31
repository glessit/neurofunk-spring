package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.configuration.security.SecurityConfiguration;
import com.glessit.neurofunky.configuration.security.beans.FacebookAuthenticationProvider;
import com.glessit.neurofunky.entity.User;
import com.glessit.neurofunky.repository.UserRepository;
import com.glessit.neurofunky.service.ISecurityService;
import com.glessit.neurofunky.service.ITokenService;
import com.glessit.neurofunky.service.IUserService;
import com.glessit.neurofunky.service.dto.FacebookToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class SecurityService implements ISecurityService {

    @Autowired
    private ITokenService tokenService;
    @Autowired
    private HttpServletRequest request;

    // todo: please use cache of security
    /*
    @Autowired
    private UserCache userCache;
    */
    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacebookAuthenticationProvider facebookAuthenticationProvider;

    @Override
    public FacebookToken login(Long id) {
        log.info("Autologin with id {}", id);
        // generate token
        User user = userRepository.findOneByFacebookId(id);
        Long token = tokenService.createTokenForUser(user, tokenService.get());
        return new FacebookToken(token, user);
    }

    @Override
    public User getAuthenticatedUser() {
        User currentUser = null;
        String authToken = request.getHeader(SecurityConfiguration.HEADER_AUTH_NAME);
        if (!StringUtils.isEmpty(authToken)) {
            currentUser = tokenService.getUserByToken(authToken);
        }
        return currentUser;
    }
}
