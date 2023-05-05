package com.trungtv.blogauth.service.mapper;

import com.trungtv.blogauth.controller.dto.UserDto;
import com.trungtv.blogauth.security.midleware.KeycloakClient;
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
