package com.trungtv.blogauth.controller;

import com.trungtv.blogauth.controller.dto.EmailDto;
import com.trungtv.blogauth.controller.response.BaseResponse;
import com.trungtv.blogauth.domain.Email;
import com.trungtv.blogauth.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/otp")
public class OtpController {
    private final EmailService emailService;

    @PostMapping(value = "/send-register")
    public ResponseEntity<BaseResponse> send(@RequestBody EmailDto payload) throws MessagingException {
//        Email email = new Email(payload);
        emailService.sendEmailRegister(payload);
        BaseResponse baseResponse = BaseResponse.builder()
                .code(200)
                .build();
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping(value = "/send-forgot-password")
    public ResponseEntity<BaseResponse> sendForgotPassword(@RequestBody EmailDto payload) throws MessagingException {
        emailService.sendEmailVerifyForgotPassword(payload);
        BaseResponse baseResponse = BaseResponse.builder()
                .code(200)
                .build();
        return ResponseEntity.ok(baseResponse);
    }

}
