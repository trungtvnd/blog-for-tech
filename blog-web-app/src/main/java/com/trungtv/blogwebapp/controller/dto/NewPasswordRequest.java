package com.trungtv.blogwebapp.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class NewPasswordRequest {
    private String email;
    private String newPassword;
    private String reTypePassword;
    private String otpToken;
}