package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.follow.FollowStatus;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.post.dto.PostProfileDto;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.domain.user.dto.ProfileDto;
import com.gokimpark.instaclone.domain.user.dto.ProfileUserInfoDto;
import com.gokimpark.instaclone.domain.user.dto.UserSimpleInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserService userService;
    private final FollowService followService;
    private final PostService postService;

    ModelMapper mapper = new ModelMapper();

    public ProfileDto getProfile(String toUsername, String fromUsername) {
        UserDto toUser = userService.findByUsername(toUsername);
        UserDto fromUser = userService.findByUsername(fromUsername);

        ProfileDto profileDto = new ProfileDto();
        ProfileUserInfoDto userInfoDto = mapper.map(toUser, ProfileUserInfoDto.class);
        userInfoDto.setRequestingUsername(fromUsername);
        if(fromUser.getId().equals(toUser.getId())) {
            profileDto.setIsOneself(true);
        }
        else {
            profileDto.setIsOneself(false);
            userInfoDto.setFollowing(followService.isFollowingById(toUser.getId(), fromUser.getId()));
        }
        List<PostProfileDto> postProfileDtoList = postService.findAllProfilePostByUser(toUser.getUsername());
        userInfoDto.setPostCount((long) postProfileDtoList.size());
        profileDto.setPosts(postProfileDtoList);

        Pair<Long, Long> followCount = followService.getProfileFollowCount(toUser.getUsername());
        userInfoDto.setFollowerCount(followCount.getFirst());
        userInfoDto.setFollowingCount(followCount.getSecond());
        profileDto.setUserInfo(userInfoDto);
        return profileDto;
    }

    public List<UserSimpleInfoDto> getAllSimpleProfile(String username) {
        List<UserDto> users = userService.getAllUser();
        List<UserSimpleInfoDto> userSimpleInfoDtoList = new ArrayList<>();
        for(UserDto user : users) {
            if(user.getUsername().equals(username)) continue;
            UserSimpleInfoDto userSimpleInfoDto = new UserSimpleInfoDto(user.getUsername(), user.getName());
            userSimpleInfoDto.setRequestingUsername(username);

            if(followService.isFollowingByUsername(user.getUsername(), username))
                userSimpleInfoDto.setFollowStatus(FollowStatus.FOLLOWING);
            else
                userSimpleInfoDto.setFollowStatus(FollowStatus.UNFOLLOW);

            userSimpleInfoDtoList.add(userSimpleInfoDto);
        }
        return userSimpleInfoDtoList;
    }
}
