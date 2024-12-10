package com.ead.authuser.service;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserModel> findAll();

    Optional<UserModel> findById(UUID userId);

    void delete(UserModel userModel);

    UserModel register(UserRecordDto userRecordDto);

    boolean existByUsername(String username);

    boolean existByEmail(String email);
}
