package com.gokimpark.instaclone.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountJoinForm {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
