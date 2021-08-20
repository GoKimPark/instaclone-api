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
    private String postCnt;
    @NotBlank
    private String followerCnt;
    @NotBlank
    private String followingCnt;


    public static ProfileUserInfoDto from(User user, String postCnt, String followerCnt, String followingCnt) {
        ProfileUserInfoDto dto = new ProfileUserInfoDto();
        dto.setProfileImageUrl(user.getProfileImageUrl());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setBio(user.getBio());
        dto.setWebSite(user.getWebsite());

        dto.postCnt = postCnt;
        dto.followerCnt = followerCnt;
        dto.followingCnt = followingCnt;
        return dto;
    }
}
