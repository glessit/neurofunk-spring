package com.glessit.neurofunky.service.standard;


import com.glessit.neurofunky.configuration.security.beans.FacebookAuthentication;
import com.glessit.neurofunky.configuration.security.beans.FacebookAuthenticationProvider;
import com.glessit.neurofunky.service.ISecurityService;
import com.glessit.neurofunky.service.IUserService;
import com.glessit.neurofunky.service.dto.FacebookToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecurityService implements ISecurityService {

    // todo: please use cache of security
    /*
    @Autowired
    private UserCache userCache;
    */
    @Autowired
    private IUserService userService;
    @Autowired
    private FacebookAuthenticationProvider facebookAuthenticationProvider;

    @Override
    public FacebookToken login(Long id) {
        log.info("Autologin with id {}", id);
        Authentication facebookAuthentication = facebookAuthenticationProvider.authenticate(new
                FacebookAuthentication(String.valueOf(id)));
        return new FacebookToken(
                Long.valueOf(facebookAuthentication.getCredentials().toString()),
                userService.getUserByFacebookId(id)
        );
    }
}
