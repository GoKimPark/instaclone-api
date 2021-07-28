package com.gokimpark.instaclone.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountEditDto {

    private String profileImageUrl;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    private String webSite;
    private String bio;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;
    private String gender;
}
