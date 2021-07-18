package com.gokimpark.instaclone.account;

import lombok.Data;

@Data
public class AccountEditDto {
    String profileImageUrl;

    String name;
    String id;
    String webSite;
    String bio;

    String email;
    String phoneNumber;
    String gender;
}
