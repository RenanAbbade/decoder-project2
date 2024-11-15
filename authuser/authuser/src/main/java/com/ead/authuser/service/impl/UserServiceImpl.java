package com.ead.authuser.service.impl;

import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
