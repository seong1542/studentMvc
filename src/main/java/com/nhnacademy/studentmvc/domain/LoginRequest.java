package com.nhnacademy.studentmvc.domain;

import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class LoginRequest {

    @NotBlank(message = "userId is empty")
    private String userId;

    @NotBlank(message ="userPassword  is empty!")
    private String userPassword;
    public String getUserId() {
        return userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
}