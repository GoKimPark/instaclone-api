package com.gokimpark.instaclone.domain.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileUserInfoDto {

    private boolean follow;
    @NotBlank
    private String name;
    @NotBlank
    private String username;

    @NotBlank
    private String profileImageUrl;
    private String webSite;
    private String bio;

    @NotBlank
    private Long postCount;
    @NotBlank
    private Long followerCount;
    @NotBlank
    private Long followingCount;
}
