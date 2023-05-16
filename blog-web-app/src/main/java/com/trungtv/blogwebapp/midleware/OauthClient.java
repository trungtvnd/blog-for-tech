package com.trungtv.blogwebapp.midleware;

import com.trungtv.blogwebapp.controller.dto.AccessTokenDTO;
import com.trungtv.blogwebapp.controller.dto.LoginDto;
import com.trungtv.blogwebapp.service.KeycloakFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@Slf4j
public class OauthClient {
    @Value("${oauth.token-url}")
    private String tokenUrl;

    @Value("${oauth.client-id}")
    private String clientId;
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${oauth.client-secret}")
    private String clientSecret;

    @Value("${oauth.scope}")
    private String scope;

    @Value("${oauth.user-info-url}")
    private String userInfoUrl;

    @Value("${oauth.user-logout-url}")
    private String userLogoutUrl;

    private final KeycloakFeign keycloakFeign;

    public OauthClient(KeycloakFeign keycloakFeign) {
        this.keycloakFeign = keycloakFeign;
    }


    public AccessTokenDTO login(LoginDto loginDto){
        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.add("client_id", clientId);
        formParams.add("client_secret", clientSecret);
        formParams.add("grant_type", "password");
        formParams.add("username", loginDto.getUsername());
        formParams.add("password", loginDto.getPassword());
        return keycloakFeign.login(formParams);
    }

    public AccessTokenDTO genToken(MultiValueMap<String, String> payloads){
        payloads.add("grant_type", payloads.containsKey("refresh_token") ? "refresh_token" : "password");
        payloads.add("client_id", clientId);
        payloads.add("client_secret", clientSecret);
        payloads.add("scope", scope);
        return keycloakFeign.login(payloads);
    }


}
