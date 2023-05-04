package com.trungtv.blogauth.service.impl;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.SecretGenerator;
import com.bastiaanjansen.otp.TOTP;
import com.trungtv.blogauth.controller.dto.EmailDto;
import com.trungtv.blogauth.service.CacheService;
import com.trungtv.blogauth.service.EmailService;
import com.trungtv.blogauth.service.GenerateOtpCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String emailFrom;
    private final JavaMailSender javaMailSender;

    private final CacheService cacheService;

    private final GenerateOtpCodeService generateOtpCodeService;


    @Override
    public void sendEmailRegister(EmailDto dto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(dto.getTo());
        mailMessage.setSubject(dto.getSubject());
        String otp= generateOtpCodeService.generateOtp();
        mailMessage.setText(otp);
        cacheService.set("KEY_REGISTER" + dto.getTo(), otp, 600);
        mailMessage.setFrom(emailFrom);
        javaMailSender.send(mailMessage);
    }

    ///gửi mail có đính kèm
    @Override
    public void sendEmailAttach(String toEmail, String subject, String message) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("to_@email");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);
    }

}
