package com.gokimpark.instaclone.web.follow;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.web.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/follow/{toUsername}/{fromUsername}")
    public ResponseEntity<?> addFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        User toUser = userService.findByUsername(toUsername);
        User fromUser = userService.findByUsername(fromUsername);
        followService.addFollow(toUser, fromUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unfollow/{toUsername}/{fromUsername}")
    public ResponseEntity<?> unFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        User toUser = userService.findByUsername(toUsername);
        User fromUser = userService.findByUsername(fromUsername);
        followService.unFollow(toUser.getId(), fromUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/follower/{username}")
    public ResponseEntity<?> getFollower(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<User> followerList = followService.getFollowerList(user.getId());

        List<ProfileListDto> followerDtoList = followerList.stream()
                .map(u -> new ProfileListDto(u))
                .collect(Collectors.toList());
        return new ResponseEntity<>(followerDtoList, HttpStatus.OK);
    }

    @GetMapping("/following/{username}")
    public ResponseEntity<?> getFollowing(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<User> followingList = followService.getFollowingList(user.getId());

        List<ProfileListDto> followingDtoList = followingList.stream()
                .map(u -> new ProfileListDto(u))
                .collect(Collectors.toList());
        return new ResponseEntity<>(followingDtoList, HttpStatus.OK);
    }

    private EditDto toEditDto(User user){
        EditDto editDto = new EditDto();
        editDto.setUserId(user.getId());
        editDto.setName(user.getName());
        editDto.setUsername(user.getUsername());
        editDto.setWebSite(user.getWebsite());
        editDto.setBio(user.getBio());
        editDto.setEmail(user.getEmail());
        editDto.setPhoneNumber(user.getPhoneNumber());
        return editDto;
    }

}
