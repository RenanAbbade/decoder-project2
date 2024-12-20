package com.ead.authuser.service.impl;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.enumerator.UserStatus;
import com.ead.authuser.enumerator.UserType;
import com.ead.authuser.exceptions.NotFoundException;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        Optional<UserModel> userModelOptional = userRepository.findById(userId);

        if (userModelOptional.isEmpty())
            throw new NotFoundException("Error: User not found!");

        return userModelOptional;
    }

    @Override
    public void delete(UserModel userModel) {
        this.userRepository.delete(userModel);
    }

    @Override
    public UserModel register(UserRecordDto userRecordDto) {
        var userModel = UserModel.builder()
                .userStatus(UserStatus.ACTIVE)
                .userType(UserType.USER)
                .creationDate(LocalDateTime.now(ZoneId.of("UTC")))
                .lastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")))
                .build();
        BeanUtils.copyProperties(userRecordDto, userModel);

        return userRepository.save(userModel);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserModel updateUser(UserRecordDto userRecordDto, UserModel userModel) {
        userModel = UserModel.builder()
                .fullName(userRecordDto.fullName())
                .phoneNumber(userRecordDto.phoneNumber())
                .lastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")))
                .build();
        return userRepository.save(userModel);
    }

    @Override
    public UserModel updatePassword(UserRecordDto userRecordDto, UserModel userModel) {
        userModel.setPassword(userRecordDto.password());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return userRepository.save(userModel);
    }

    @Override
    public UserModel updateImage(UserRecordDto userRecordDto, UserModel userModel) {
        userModel.setImageUrl(userRecordDto.imageUrl());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return userRepository.save(userModel);
    }

    @Override
    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
