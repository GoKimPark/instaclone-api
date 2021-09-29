package com.gokimpark.instaclone.web.follow;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.follow.dto.FollowSimpleListDto;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.web.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{toUsername}/{fromUsername}")
    public ResponseEntity<?> addFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        followService.addFollow(toUsername, fromUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unfollow/{toUsername}/{fromUsername}")
    public ResponseEntity<?> unFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        followService.unFollow(toUsername, fromUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/follower/{username}")
    public ResponseEntity<?> getFollower(@PathVariable String username){
        List<FollowSimpleListDto> followerList = followService.getFollowerList(username);
        return new ResponseEntity<>(followerList, HttpStatus.OK);
    }

    @GetMapping("/following/{username}")
    public ResponseEntity<?> getFollowing(@PathVariable String username){
        List<FollowSimpleListDto> followingList = followService.getFollowingList(username);
        return new ResponseEntity<>(followingList, HttpStatus.OK);
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
