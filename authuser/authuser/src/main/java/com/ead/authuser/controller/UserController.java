package com.ead.authuser.controller;


import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(
            //@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) -> Configuração feita em ResolverConfig
            Pageable pageable) { //Page inicia da pagina 0 e size, a quantidade de recursos por página, sort seria ordenação, e direção ASC ou DESC
        Page<UserModel> userModelPage = userService.findAll(pageable);
        return ResponseEntity.ok(userModelPage);
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
                                             @RequestBody @Validated(UserRecordDto.UserView.UserPut.class)
                                             @JsonView(UserRecordDto.UserView.UserPut.class)
                                             UserRecordDto userRecordDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userRecordDto, userService.findById(userId).get()));
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody @Validated(UserRecordDto.UserView.PasswordPut.class)
                                             @JsonView(UserRecordDto.UserView.PasswordPut.class)
                                             UserRecordDto userRecordDto){

        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(userModelOptional.get().getPassword().equals(userRecordDto.oldPassword())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatch old password!");
        }
        userService.updatePassword(userRecordDto, userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Password updated!");
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID userId,
                                                 @RequestBody @Validated(UserRecordDto.UserView.ImagePut.class)
                                                 @JsonView(UserRecordDto.UserView.ImagePut.class)
                                                 UserRecordDto userRecordDto){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateImage(userRecordDto, userModelOptional.get()));
    }
}

