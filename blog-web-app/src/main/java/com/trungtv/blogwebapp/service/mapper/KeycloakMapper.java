package com.trungtv.blogwebapp.service.mapper;


import com.trungtv.blogwebapp.controller.dto.UserDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KeycloakMapper {

    default UserRepresentation toRepresentationDto(UserDto dto) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(dto.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(dto.getUsername());
        userRepresentation.setCredentials(List.of(KeycloakClient.credentialOf(dto.getPassword())));

        return userRepresentation;
    }
}
