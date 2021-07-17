package com.gokimpark.instaclone.profile;

import lombok.Data;

@Data
public class ProfileMemberInfoDto {
    String profileImageUrl;

    String id;
    String name;
    String bio;
    String webSite;

    Long postCnt;
    Long followerCnt;
    Long followingCnt;
}
