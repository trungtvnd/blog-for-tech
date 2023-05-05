package com.trungtv.blogauth.controller;

import com.trungtv.blogauth.controller.dto.LoginDto;
import com.trungtv.blogauth.controller.dto.RegisterDto;
import com.trungtv.blogauth.controller.response.BaseResponse;
import com.trungtv.blogauth.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {
    private final KeycloakService keycloakService;
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login (@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(BaseResponse
                .builder()
                .code(200)
                .data(keycloakService.login(loginDto))
                .build());
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<BaseResponse> register (@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(BaseResponse
                .builder()
                .code(200)
                .data(keycloakService.register(registerDto))
                .build());
    }


}
