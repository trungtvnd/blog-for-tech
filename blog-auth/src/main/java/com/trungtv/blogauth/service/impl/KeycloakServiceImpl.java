package com.trungtv.blogauth.service.impl;

import com.trungtv.blogauth.controller.dto.AccessTokenDTO;
import com.trungtv.blogauth.controller.dto.LoginDto;
import com.trungtv.blogauth.controller.dto.RegisterDto;
import com.trungtv.blogauth.domain.UserDto;
import com.trungtv.blogauth.exception.CustomBusinessException;
import com.trungtv.blogauth.security.midleware.KeycloakClient;
import com.trungtv.blogauth.security.midleware.OauthClient;
import com.trungtv.blogauth.service.KeycloakService;
import com.trungtv.blogauth.service.mapper.KeycloakMapper;
import com.trungtv.blogauth.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private final KeycloakClient keycloakClient;

    private final OauthClient oauthClient;

    private final KeycloakMapper keycloakMapper;

    private final UserMapper userMapper;

    @Override
    public AccessTokenDTO login(LoginDto loginDto) {
        AccessTokenDTO response =  oauthClient.login(loginDto);
        if (ObjectUtils.isEmpty(response)){
            throw new CustomBusinessException("invalid.user.or.password");
        }
        return response;
    }

    public UserRepresentation findAllUserByUsername(String username) {
        return keycloakClient.findAllUserByUsername(username)
                .stream().filter(s -> !ObjectUtils.isEmpty(s) && s.isEnabled()).findFirst().orElse(null);
    }

    @Override
    public AccessTokenDTO register(RegisterDto registerDto) {
        //validate
        validateRegister(registerDto);
        UserDto userDto = userMapper.toDtoFromRegister(registerDto);
        UserRepresentation userRepresentation = keycloakMapper.toRepresentationDto(userDto);
        keycloakClient.createUser(userRepresentation, true);
        var existedUser = findAllUserByUsername(registerDto.getUsername());
        if (ObjectUtils.isEmpty(existedUser)) throw new CustomBusinessException("invalid.user.or.password");
        return login(new LoginDto(registerDto.getUsername(), registerDto.getPassword()));
    }

    public void validateRegister(RegisterDto dto){
        // validate
    }

}
