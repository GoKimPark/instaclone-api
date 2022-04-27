package com.gokimpark.instaclone.web.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileRequestDto {

    @NotBlank
    private String toUsername;

    @NotBlank
    private String fromUsername;
}
