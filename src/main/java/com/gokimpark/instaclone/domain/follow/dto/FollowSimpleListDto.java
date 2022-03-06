package com.gokimpark.instaclone.domain.follow.dto;

import lombok.Data;

@Data
public class FollowSimpleListDto {

    private String profileImageUrl;
    private String username;

    public FollowSimpleListDto(String username) {
        this.profileImageUrl = null;
        this.username = username;
    }
}
