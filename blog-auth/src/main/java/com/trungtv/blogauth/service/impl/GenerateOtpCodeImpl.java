package com.trungtv.blogauth.service.impl;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.SecretGenerator;
import com.bastiaanjansen.otp.TOTP;
import com.trungtv.blogauth.service.GenerateOtpCodeService;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class GenerateOtpCodeImpl implements GenerateOtpCodeService {

    private static final TOTP totp = new TOTP.Builder(SecretGenerator.generate())
            .withPasswordLength(6)
            .withAlgorithm(HMACAlgorithm.SHA512)
            .withPeriod(Duration.ofSeconds(60)).build();

    @Override
    public String generateOtp() {
        String otp = totp.now().substring(1, 5);
        return otp;
    }
}
