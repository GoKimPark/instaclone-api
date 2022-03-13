package com.gokimpark.instaclone.web.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileRequestDto {

    @NotBlank
    private String targetUsername;

    @NotBlank
    private String requestedUsername;
}
