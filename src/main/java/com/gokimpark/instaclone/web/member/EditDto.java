package com.gokimpark.instaclone.web.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EditDto {

    private String profileImageUrl;

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
    private String gender;
}
