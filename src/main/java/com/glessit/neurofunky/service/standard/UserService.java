package com.glessit.neurofunky.service.standard;

import com.glessit.neurofunky.entity.Role;
import com.glessit.neurofunky.entity.User;
import com.glessit.neurofunky.entity.type.RoleType;
import com.glessit.neurofunky.repository.RoleRepository;
import com.glessit.neurofunky.repository.UserRepository;
import com.glessit.neurofunky.service.IUserService;
import com.glessit.neurofunky.web.facebook.dto.FacebookUserDto;
import com.glessit.neurofunky.web.facebook.dto.UserDTO;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

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

    @Override
    public boolean isFacebookUserExist(Long id) {
        return userRepository.findOneByFacebookId(id) == null ? false : true;
    }

    @Override
    public User getUserByFacebookId(Long id) {
        return userRepository.findOneByFacebookId(id);
    }

    /* created users has only read-role */
    @Override
    @Transactional
    public void createFacebookUser(FacebookUserDto facebookUser) {

        Role role = new Role();
        role.setType(RoleType.SIMPLE_USER);
        log.info("Create new facebook user with first name: {} and id {}", facebookUser.getFirstName(), facebookUser.getId());
        User user = new User();
        user.setFacebookId(facebookUser.getId());
        user.setLastName(facebookUser.getLastName());
        user.setFirstName(facebookUser.getFirstName());
        user.setRoles(Sets.newHashSet(role));
        userRepository.save(user);
    }
}
