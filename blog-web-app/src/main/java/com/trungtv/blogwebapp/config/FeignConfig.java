package com.trungtv.blogwebapp.config;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/x-www-form-urlencoded", "application/json");
            requestTemplate.header("Accept", "application/x-www-form-urlencoded", "application/json");
        };
    }

    /**
     * Enable this bean if you want to add basic Authorization header
     * for e.g. Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("username", "password");
    }

}
