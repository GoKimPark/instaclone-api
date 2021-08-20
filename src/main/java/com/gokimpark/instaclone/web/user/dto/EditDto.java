package com.gokimpark.instaclone.web.user.dto;

import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EditDto {

    @ReadOnlyProperty
    private Long userId;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    private String webSite;
    private String bio;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String phoneNumber;
}