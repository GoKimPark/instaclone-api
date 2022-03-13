package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.post.dto.PostProfileDto;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.domain.user.dto.ProfileDto;
import com.gokimpark.instaclone.domain.user.dto.ProfileUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserService userService;
    private final FollowService followService;
    private final PostService postService;

    ModelMapper mapper = new ModelMapper();

    public ProfileDto getProfile(String targetUsername, String requestedUsername) {
        UserDto targetUser = userService.findByUsername(targetUsername);
        UserDto requestedUser = userService.findByUsername(requestedUsername);

        ProfileUserInfoDto userInfoDto = mapper.map(targetUser, ProfileUserInfoDto.class);
        if(requestedUser.getId().equals(targetUser.getId()))
            userInfoDto.setFollow(true);
        else
            userInfoDto.setFollow(followService.isFollow(targetUser.getId(), requestedUser.getId()));

        List<PostProfileDto> postProfileDtoList = postService.findAllProfilePostByUser(targetUser.getUsername());

        Pair<Long, Long> followCount = followService.getProfileFollowCount(targetUser.getUsername());
        userInfoDto.setFollowerCount(followCount.getFirst());
        userInfoDto.setFollowingCount(followCount.getSecond());

        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserInfo(userInfoDto);
        profileDto.setPosts(postProfileDtoList);
        return profileDto;
    }
}
