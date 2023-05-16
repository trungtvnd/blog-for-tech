package com.trungtv.blogwebapp.service.impl;


import com.trungtv.blogwebapp.constant.Constant;
import com.trungtv.blogwebapp.constant.ConstantErrorCode;
import com.trungtv.blogwebapp.controller.dto.*;
import com.trungtv.blogwebapp.exception.CustomBusinessException;
import com.trungtv.blogwebapp.midleware.OauthClient;
import com.trungtv.blogwebapp.service.CacheService;
import com.trungtv.blogwebapp.service.KeycloakService;
import com.trungtv.blogwebapp.service.UserService;
import com.trungtv.blogwebapp.service.mapper.KeycloakMapper;
import com.trungtv.blogwebapp.service.mapper.UserMapper;
import com.trungtv.blogwebapp.util.ExceptionUtil;
import com.trungtv.blogwebapp.util.MessageUtils;
import com.trungtv.blogwebapp.util.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {
    private final KeycloakClient keycloakClient;
    private final OauthClient oauthClient;
    private final KeycloakMapper keycloakMapper;
    private final UserMapper userMapper;
    private final CacheService cacheService;
    private final UserService userService;

    @Override
    public AccessTokenDTO login(LoginDto loginDto) {
        AccessTokenDTO response = oauthClient.login(loginDto);
        if (ObjectUtils.isEmpty(response)) {
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
        if (ObjectUtils.isEmpty(existedUser))
            throw new CustomBusinessException(ConstantErrorCode.ErrorCode.ENTITY_NOT_FOUND);
        userService.create(userMapper.toEntity(userDto));
        return login(new LoginDto(registerDto.getUsername(), registerDto.getPassword()));
    }

    public void validateRegister(RegisterDto dto) {
        // validate
        log.info("AuthUseCase:: validateVerifyToken >> " + dto.getEmail());
        Assert.hasText(dto.getEmail(), MessageUtils.getMessage(ExceptionUtil.getMessageError(ConstantErrorCode.ValidateErrorCode.EMAIL_MUST_NOT_BE_NULL)));
        Assert.hasText(dto.getUsername(), MessageUtils.getMessage(ExceptionUtil.getMessageError(ConstantErrorCode.ValidateErrorCode.USERNAME_MUST_NOT_BE_NULL)));
        Assert.hasText(dto.getPassword(), MessageUtils.getMessage(ExceptionUtil.getMessageError(ConstantErrorCode.ValidateErrorCode.PASSWORD_MUST_NOT_BE_NULL)));
        if (!PasswordValidator.isValid(dto.getPassword()))
            throw new CustomBusinessException(ConstantErrorCode.ValidateErrorCode.PASSWORD_WRONG_FORMAT);
        String existedToken = cacheService.get(Constant.RedisConstant.KEY_REGISTER + dto.getEmail());
        Assert.hasText(existedToken, MessageUtils.getMessage(ExceptionUtil.getMessageError(ConstantErrorCode.ValidateErrorCode.OTP_NOT_EXISTED)));
        Assert.isTrue(existedToken.equals(dto.getOtpToken()), MessageUtils.getMessage(ExceptionUtil.getMessageError(ConstantErrorCode.ValidateErrorCode.OTP_WRONG)));
    }

    @Override
    public AccessTokenDTO refreshToken(String token) {
        MultiValueMap<String, String> payloads = new LinkedMultiValueMap<>();
        payloads.add("refresh_token", token);
        return oauthClient.genToken(payloads);
    }

    @Override
    public void changePassword(ChangePasswordDto dto) {
        //validate

        keycloakClient.changePassword(dto.getUsername(), dto.getPassword());
    }

    @Override
    public void forgotPassword(NewPasswordRequest dto) {
        //validate
    }
}
