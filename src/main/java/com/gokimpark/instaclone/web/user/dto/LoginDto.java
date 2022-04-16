package com.gokimpark.instaclone.web.user.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
