package com.gokimpark.instaclone.domain.follow.dto;

import lombok.Data;

@Data
public class UserSimpleInfoDto {

    private String requestedUsername;
    private Boolean isFollowing;

    private String username;
    private String name;

    public UserSimpleInfoDto(String username, String name) {
        this.username = username;
        this.name = name;
    }
}
