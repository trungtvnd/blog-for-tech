package com.trungtv.blogauth.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private String phoneNumber;
    private Integer isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Integer loginCount;
    private Integer gender;
    private Date dateOfBirth;
    private Integer isActive;
    private String accessToken;
    private String refreshToken;
    private Integer userType;

}
