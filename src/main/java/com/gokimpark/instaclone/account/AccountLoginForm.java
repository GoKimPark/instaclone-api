package com.gokimpark.instaclone.account;

import lombok.Data;

@Data
public class AccountLoginForm {
    private String EmailOrUsernameOrPhoneNumber;
    private String password;
}
