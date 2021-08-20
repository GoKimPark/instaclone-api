package com.gokimpark.instaclone.web.user.dto;

import com.gokimpark.instaclone.domain.like.Likes;
import com.gokimpark.instaclone.domain.user.User;
import lombok.Data;

@Data
public class ProfileListDto {

    private String profileImageUrl;
    private String username;

    public ProfileListDto(User user){
        this.profileImageUrl = user.getProfileImageUrl();
        this.username = user.getUsername();
    }
}
