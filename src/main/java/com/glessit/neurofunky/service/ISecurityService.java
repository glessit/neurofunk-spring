package com.glessit.neurofunky.service;


import com.glessit.neurofunky.service.dto.FacebookToken;

public interface ISecurityService {
    FacebookToken login(Long id);
}
