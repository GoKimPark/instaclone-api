package com.gokimpark.instaclone.account;

import javax.validation.constraints.NotEmpty;

public class MemberLoginInfo {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public String getEmail() {
        return email;
    }
}
