package com.gokimpark.instaclone.account;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccountJoinForm {
    String phoneNumber;
    String email;
    String username;
    String name;

    //@NotEmpty
    String password;
}
