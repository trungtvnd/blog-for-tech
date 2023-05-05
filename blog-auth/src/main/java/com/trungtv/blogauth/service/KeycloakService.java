package com.trungtv.blogauth.service;

import com.trungtv.blogauth.controller.dto.*;
import com.trungtv.blogauth.controller.response.BaseResponse;

public interface KeycloakService {
    AccessTokenDTO login(LoginDto loginDto);

    AccessTokenDTO register(RegisterDto registerDto);

    AccessTokenDTO refreshToken(String token);

    void changePassword(ChangePasswordDto dto);
    void forgotPassword(NewPasswordRequest dto);
}

