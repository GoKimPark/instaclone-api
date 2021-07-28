package com.gokimpark.instaclone.profile;

import com.gokimpark.instaclone.member.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProfileMemberInfoDto {

    public static ProfileMemberInfoDto from(Member member) {
        ProfileMemberInfoDto dto = new ProfileMemberInfoDto();
        dto.setFollowerCnt(member.getFollowerCnt().toString());
        dto.setFollowingCnt(member.getFollowingCnt().toString());
        dto.setPostCnt(member.getPostCnt().toString());
        return dto;
    }

    @NotBlank
    private String profileImageUrl;

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    private String bio;
    private String webSite;

    @NotBlank
    private String postCnt;

    @NotBlank
    private String followerCnt;

    @NotBlank
    private String followingCnt;
}
