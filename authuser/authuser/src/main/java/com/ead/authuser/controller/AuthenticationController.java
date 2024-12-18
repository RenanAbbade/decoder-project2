package com.ead.authuser.controller;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    final UserService userService;


    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserRecordDto.UserView.RegistrationPost.class)
                                                   @JsonView(UserRecordDto.UserView.RegistrationPost.class)
                                                   UserRecordDto userRecordDto){
        if(userService.existByUsername(userRecordDto.username())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This username has already been taking.");
        }
        if(userService.existByUsername(userRecordDto.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This Email has already been taking.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userRecordDto));

    }
}
