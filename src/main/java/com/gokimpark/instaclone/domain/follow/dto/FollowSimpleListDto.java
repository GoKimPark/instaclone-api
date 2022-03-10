package com.gokimpark.instaclone.domain.follow.dto;

import lombok.Data;

@Data
public class FollowSimpleListDto {

    private String profileImageUrl;
    private String username;
    private String name;

    public FollowSimpleListDto(String username, String name) {
        this.profileImageUrl = null;
        this.username = username;
        this.name = name;
    }
}
