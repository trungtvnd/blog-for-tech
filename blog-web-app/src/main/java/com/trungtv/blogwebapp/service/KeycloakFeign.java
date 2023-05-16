package com.trungtv.blogwebapp.service;

import com.trungtv.blogwebapp.config.FeignConfig;
import com.trungtv.blogwebapp.controller.dto.AccessTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "${feign.application-name.keycloak-server.name}", configuration = FeignConfig.class
        , url = "${feign.application-name.keycloak-server.url}")
public interface KeycloakFeign {

    @PostMapping("realms/blog-for-tech/protocol/openid-connect/token")
    AccessTokenDTO login(MultiValueMap<String, ?> formParams);

}
