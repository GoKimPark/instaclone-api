package com.gokimpark.instaclone.web.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
