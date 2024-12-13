package com.ead.authuser.dto;

import com.fasterxml.jackson.annotation.JsonView;

public record UserRecordDto(
        @JsonView(UserView.RegistrationPost.class) String username,
        //com a annotation @JsonView, posso definir qual sera a camada de view, ou seja, o atributo só será visualizado na camada
        @JsonView(UserView.RegistrationPost.class) String email,
        @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class}) String password,
        @JsonView(UserView.PasswordPut.class) String oldPassword,
        @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class}) String fullName,
        @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class}) String phoneNumber,
        @JsonView(UserView.ImagePut.class)  String imageUrl) {

    //JSON View - Multiplas visões
    public interface UserView{
        interface RegistrationPost {}
        interface UserPut {}
        interface PasswordPut {}
        interface ImagePut {}
    }
}
