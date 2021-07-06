package com.gokimpark.instaclone.account;

import javax.validation.constraints.NotEmpty;

public class MemberJoinInfo  {
    @NotEmpty
    private String PhoneNumber;

    @NotEmpty
    private String email;

    @NotEmpty
    private String UserId;

    @NotEmpty
    private String UserName;

    @NotEmpty
    private String password;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return password;
    }
}
