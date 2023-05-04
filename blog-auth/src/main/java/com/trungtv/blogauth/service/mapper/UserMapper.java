package com.trungtv.blogauth.service.mapper;

import com.trungtv.blogauth.controller.dto.RegisterDto;
import com.trungtv.blogauth.domain.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDtoFromRegister(RegisterDto registerDto);
}
