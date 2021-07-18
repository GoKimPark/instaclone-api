package com.gokimpark.instaclone.profile;

import com.gokimpark.instaclone.member.Member;
import lombok.Data;

@Data
public class ProfileMemberInfoDto {

    public static ProfileMemberInfoDto from(Member member) {
        ProfileMemberInfoDto dto = new ProfileMemberInfoDto();
        dto.setFollowerCnt(member.getFollowerCnt().toString());
        dto.setFollowingCnt(member.getFollowingCnt().toString());
        dto.setPostCnt(member.getPostCnt().toString());
        return dto;
    }

    String profileImageUrl;

    String id;
    String name;
    String bio;
    String webSite;

    String postCnt;
    String followerCnt;
    String followingCnt;
}
