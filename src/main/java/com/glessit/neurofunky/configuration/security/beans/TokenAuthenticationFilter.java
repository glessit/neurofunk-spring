package com.glessit.neurofunky.configuration.security.beans;


import com.glessit.neurofunky.configuration.security.SecurityConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class TokenAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private FacebookAuthenticationProvider facebookAuthenticationProvider;

    @Override
    public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        // extract token from header
        final String accessToken = httpRequest.getHeader(SecurityConfiguration.HEADER_AUTH_NAME);
        if (null != accessToken) {
            Authentication authResult = facebookAuthenticationProvider.authenticate(new FacebookAuthentication(accessToken));
            if (authResult.isAuthenticated()) {
            }
        }

        chain.doFilter(request, response);
    }
}
