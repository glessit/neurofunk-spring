package com.glessit.neurofunky.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = MAPPING.LOGOUT)
@Slf4j
public class LogoutController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Boolean logout() {
        // todo: please implement this later!
        /* find token remove it, then return status of logout */
        return null;
    }
}
