package com.gokimpark.instaclone.domain.follow.dto;

import com.gokimpark.instaclone.domain.user.User;
import lombok.Data;

@Data
public class FollowSimpleListDto {

    private String profileImageUrl;
    private String username;

    public FollowSimpleListDto(User user) {
        this.profileImageUrl = user.getProfileImageUrl();
        this.username = user.getUsername();
    }
}
