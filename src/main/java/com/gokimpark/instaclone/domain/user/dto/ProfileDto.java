package com.gokimpark.instaclone.domain.user.dto;

import com.gokimpark.instaclone.domain.post.dto.PostProfileDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProfileDto {

    Boolean isOneself;
    ProfileUserInfoDto userInfo;
    List<PostProfileDto> posts;
    List<ProfileStoryDto> stories;
}