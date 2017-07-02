package com.glessit.neurofunky.service.dto;

import lombok.Getter;

@Getter
public class FacebookToken {
    private final Long token;
    public FacebookToken(Long token) {
        this.token = token;
    }
}
