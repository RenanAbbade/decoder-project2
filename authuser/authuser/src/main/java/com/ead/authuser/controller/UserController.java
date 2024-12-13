package com.ead.authuser.controller;


import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId).get());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object>  deleteUser(@PathVariable(value = "userId") UUID userId) {
        this.userService.delete(userService.findById(userId).get());
        return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody @JsonView(UserRecordDto.UserView.UserPut.class) UserRecordDto userRecordDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userRecordDto, userService.findById(userId).get()));
    }
}

