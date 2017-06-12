package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.User;
import com.glessit.neurofunky.repository.UserRepository;
import com.glessit.neurofunky.service.IUserService;
import com.glessit.neurofunky.web.facebook.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(UserDTO user) {
        return null;
    }

    @Override
    public void login(UserDTO user) {

    }

    @Override
    public void ban(UserDTO user) {

    }
}
