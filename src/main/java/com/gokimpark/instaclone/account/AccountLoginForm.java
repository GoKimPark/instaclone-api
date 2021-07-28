package com.gokimpark.instaclone.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountLoginForm {

    @NotBlank
    private String EmailOrUsernameOrPhoneNumber;

    @NotBlank
    private String password;
}
