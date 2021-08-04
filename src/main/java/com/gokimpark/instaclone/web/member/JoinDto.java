package com.gokimpark.instaclone.web.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JoinDto {

    @NotBlank
    private String joinId;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
