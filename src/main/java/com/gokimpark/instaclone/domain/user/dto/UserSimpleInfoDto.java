package com.gokimpark.instaclone.domain.user.dto;

import com.gokimpark.instaclone.domain.follow.FollowStatus;
import lombok.Data;

@Data
public class UserSimpleInfoDto {

    private String requestedUsername;
    private FollowStatus followStatus;

    private String username;
    private String name;

    public UserSimpleInfoDto(String username, String name) {
        this.username = username;
        this.name = name;
    }
}
