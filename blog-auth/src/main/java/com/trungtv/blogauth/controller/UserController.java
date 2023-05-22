package com.trungtv.blogauth.controller;

import com.trungtv.blogauth.controller.dto.ChangePasswordDto;
import com.trungtv.blogauth.controller.dto.LoginDto;
import com.trungtv.blogauth.controller.dto.NewPasswordRequest;
import com.trungtv.blogauth.controller.dto.RegisterDto;
import com.trungtv.blogauth.controller.response.BaseResponse;
import com.trungtv.blogauth.security.midleware.KeycloakClient;
import com.trungtv.blogauth.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {
    private final KeycloakService keycloakService;
    private final KeycloakClient keycloakClient;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(BaseResponse
                .builder()
                .code(200)
                .data(keycloakService.login(loginDto))
                .build());
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<BaseResponse> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(BaseResponse
                .builder()
                .code(200)
                .data(keycloakService.register(registerDto))
                .build());
    }

    @GetMapping(value = "/token/refresh")
    public ResponseEntity<BaseResponse> refreshToken(@NotBlank(message = "{common.error.must.be.not.null}") String token) {
        return ResponseEntity.ok(BaseResponse.builder()
                .code(200)
                .data(keycloakService.refreshToken(token))
                .build());
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<BaseResponse> changePassword(@RequestBody ChangePasswordDto dto) {
        keycloakService.changePassword(dto);
        return ResponseEntity.ok(BaseResponse.builder()
                .code(200)
                .build());
    }

    @PostMapping(value = "/forgot-password")
    public ResponseEntity<BaseResponse> forgotPassword(@RequestBody NewPasswordRequest newPasswordRequest) {
        keycloakService.forgotPassword(newPasswordRequest);
        return ResponseEntity.ok(BaseResponse.builder()
                .code(200)
                .build());
    }

    @PostMapping(value = "/get-user")
    public ResponseEntity<BaseResponse> getUser() {

        return ResponseEntity.ok(BaseResponse.builder()
                .data(keycloakClient.getUserSessions("trungtvnd"))
                .code(200)
                .build());
    }
}
