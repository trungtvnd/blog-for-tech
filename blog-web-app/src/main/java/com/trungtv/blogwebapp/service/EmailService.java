package com.trungtv.blogwebapp.service;


import com.trungtv.blogwebapp.controller.dto.EmailDto;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;

public interface EmailService {
    @Async
    public void sendEmailRegister(EmailDto dto);

    @Async
    public void sendEmailAttach(String toEmail, String subject, String message) throws MessagingException;

    void sendEmailVerifyForgotPassword(EmailDto mail) throws MessagingException;

}
