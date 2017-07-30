package com.glessit.neurofunky.configuration.security.beans;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
public class FacebookAuthentication implements Authentication {

    private Long facebookId;
    private Set<GrantedAuthority> authorities;
    private String accessToken;
    private boolean authenticated = false;
    private Object details;

    public FacebookAuthentication(String accessToken) {
        this.accessToken = accessToken;
    }

    public FacebookAuthentication(
            Long fbId,
            String token,
            Set<GrantedAuthority> grantedAuthorities,
            String ipAddress) {
        facebookId = fbId;
        authorities = Collections.unmodifiableSet(grantedAuthorities);
        accessToken = token;
        details = ipAddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return facebookId;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}
