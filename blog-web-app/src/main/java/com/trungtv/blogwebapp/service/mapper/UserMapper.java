package com.trungtv.blogwebapp.service.mapper;


import com.trungtv.blogwebapp.controller.dto.RegisterDto;
import com.trungtv.blogwebapp.controller.dto.UserDto;
import com.trungtv.blogwebapp.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDtoFromRegister(RegisterDto registerDto);
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
