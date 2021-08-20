package com.gokimpark.instaclone.web.user.dto;

import com.gokimpark.instaclone.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProfileDto {

    ProfileUserInfoDto userInfo;
    List<ProfilePostDto> posts;
    List<ProfileStoryDto> stories;

    public ProfileDto(User user, String postCnt, String followerCnt, String followingCnt, List<ProfilePostDto> profilePostDtos, List<ProfileStoryDto> profileStoryDtos){
        userInfo =  ProfileUserInfoDto.from(user, postCnt, followerCnt, followingCnt);
        posts = profilePostDtos;
        stories = profileStoryDtos;
    }
}