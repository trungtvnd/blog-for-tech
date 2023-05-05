package com.trungtv.blogauth.domain;

import com.trungtv.blogauth.controller.dto.EmailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "email")
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {

    private static final long serialVersionUID = 1948537045807728608L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "to")
    private String to;

    @Column(name = "cc")
    private String cc;

    @Column(name = "bcc")
    private String bcc;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @Column(name = "attachment")
    private String attachment;

    @Column(name = "source")
    private String source;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "error")
    private String error;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    public Email(EmailDto dto){
        BeanUtils.copyProperties(dto, this);
    }
}