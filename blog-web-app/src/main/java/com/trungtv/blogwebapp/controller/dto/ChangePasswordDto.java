package com.trungtv.blogwebapp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ChangePasswordDto{

    String username;
    String password;
    String retypePassword;
}
