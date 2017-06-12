package com.glessit.neurofunky.service;


import com.glessit.neurofunky.entity.User;
import com.glessit.neurofunky.web.facebook.dto.UserDTO;

public interface IUserService {

    User create(UserDTO user);
    void login(UserDTO user);
    void ban(UserDTO user);
}
