package com.trungtv.blogwebapp.service;


import com.trungtv.blogwebapp.controller.dto.*;

public interface KeycloakService {
    AccessTokenDTO login(LoginDto loginDto);

    AccessTokenDTO register(RegisterDto registerDto);

    AccessTokenDTO refreshToken(String token);

    void changePassword(ChangePasswordDto dto);
    void forgotPassword(NewPasswordRequest dto);
}

