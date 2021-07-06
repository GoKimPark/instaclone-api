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
}
