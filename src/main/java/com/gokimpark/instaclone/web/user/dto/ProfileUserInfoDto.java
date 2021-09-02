package com.gokimpark.instaclone.web.user.dto;

import com.gokimpark.instaclone.domain.user.User;
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
    private String postCount;
    @NotBlank
    private String followerCount;
    @NotBlank
    private String followingCount;


    public static ProfileUserInfoDto from(User user, String postCount, String followerCount, String followingCount) {
        ProfileUserInfoDto dto = new ProfileUserInfoDto();
        dto.setProfileImageUrl(user.getProfileImageUrl());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setBio(user.getBio());
        dto.setWebSite(user.getWebsite());

        dto.postCount = postCount;
        dto.followerCount = followerCount;
        dto.followingCount = followingCount;
        return dto;
    }
}
