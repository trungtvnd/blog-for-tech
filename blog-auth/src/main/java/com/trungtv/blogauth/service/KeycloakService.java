package com.trungtv.blogauth.service;

import com.trungtv.blogauth.controller.dto.AccessTokenDTO;
import com.trungtv.blogauth.controller.dto.LoginDto;
import com.trungtv.blogauth.controller.dto.RegisterDto;
import com.trungtv.blogauth.controller.response.BaseResponse;

public interface KeycloakService {
    AccessTokenDTO login(LoginDto loginDto);

    AccessTokenDTO register(RegisterDto registerDto);
}
