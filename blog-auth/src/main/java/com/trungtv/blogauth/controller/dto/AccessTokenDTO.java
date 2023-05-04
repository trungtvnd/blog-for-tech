package com.trungtv.blogauth.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class AccessTokenDTO {
    private String access_token;
    private Integer expires_in;
    private Integer refresh_expires_in;
    private String refresh_token;
    private String token_type;
    @JsonProperty("not-before-policy")
    private Integer not_before_policy;
    private String session_state;
    private String scope;
}
