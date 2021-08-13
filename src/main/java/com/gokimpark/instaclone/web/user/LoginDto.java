package com.gokimpark.instaclone.web.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    @Size(min = 6)
    private String password;
}
