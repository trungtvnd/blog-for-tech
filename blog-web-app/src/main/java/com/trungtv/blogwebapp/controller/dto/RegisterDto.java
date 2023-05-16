package com.trungtv.blogwebapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class RegisterDto implements Serializable {
    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String otpToken;
    private Integer userType;
}
