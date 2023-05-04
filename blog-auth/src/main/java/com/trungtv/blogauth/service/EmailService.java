package com.trungtv.blogauth.service;

import com.trungtv.blogauth.controller.dto.EmailDto;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;

public interface EmailService {
    @Async
    public void sendEmailRegister(EmailDto dto);

    @Async
    public void sendEmailAttach(String toEmail, String subject, String message) throws MessagingException;

}
