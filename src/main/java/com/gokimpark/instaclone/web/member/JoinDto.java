package com.gokimpark.instaclone.web.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JoinDto {

    @NotBlank
    private String joinId;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;
}
