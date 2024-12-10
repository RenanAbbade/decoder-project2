package com.ead.authuser.dto;

public record UserRecordDto(String username,
                            String email,
                            String password,
                            String fullName,
                            String phoneNumber,
                            String imageUrl) {
}
