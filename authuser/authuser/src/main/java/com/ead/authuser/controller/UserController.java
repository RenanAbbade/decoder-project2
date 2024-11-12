package com.ead.authuser.controller;


import com.ead.authuser.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
