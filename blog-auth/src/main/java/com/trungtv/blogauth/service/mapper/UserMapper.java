package com.trungtv.blogauth.service.mapper;

import com.trungtv.blogauth.controller.dto.RegisterDto;
import com.trungtv.blogauth.controller.dto.UserDto;
import com.trungtv.blogauth.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDtoFromRegister(RegisterDto registerDto);
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
