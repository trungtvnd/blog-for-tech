package com.trungtv.blogwebapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.time.LocalDateTime;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
public class PostDto {
    private Long id;
    private Integer isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private String title;
    private String content;
    private String description;
    private String avatarPost;
    private Integer status;
    private Long hashTagId;
    private Long userId;
}
