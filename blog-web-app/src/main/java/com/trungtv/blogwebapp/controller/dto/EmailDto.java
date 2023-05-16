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
public class EmailDto implements Serializable {
    private String email;
    private String subject;
    private String to;
    private String cc;
    private String bcc;
    private String body;
    private String attachment;
    private String source;
    private String purpose;

}