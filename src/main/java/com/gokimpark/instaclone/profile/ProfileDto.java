package com.gokimpark.instaclone.profile;

import lombok.Data;

@Data
public class ProfileDto {
    /*
    public static ProfileDto from(Profile profile) {
        return ProfileDto.builder()
                .memberInfo()
                .build();
    }
    */

    ProfileMemberInfoDto memberInfo;
    ProfilePostDto posts;
    ProfileStoryDto stories;
}