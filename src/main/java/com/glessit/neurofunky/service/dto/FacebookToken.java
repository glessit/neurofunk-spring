package com.glessit.neurofunky.service.dto;

import com.glessit.neurofunky.entity.User;
import lombok.Getter;
import lombok.Setter;

public class FacebookToken {

    @Getter
    private Boolean status = true;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    private final Long token;
    public FacebookToken(Long token, User user) {
        this.token = token;
        this.firstName = user.getFirstName();
        this.lastName = user.getFirstName();
    }
}
