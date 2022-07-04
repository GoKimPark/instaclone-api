package com.gokimpark.instaclone.web.follow;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.user.dto.UserSimpleInfoDto;
import com.gokimpark.instaclone.domain.user.ProfileService;
import com.gokimpark.instaclone.domain.user.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowApiController {

    private final FollowService followService;
    private final ProfileService profileService;

    @PostMapping("/follow/{toUsername}/{fromUsername}")
    public ResponseEntity<?> addFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        Boolean result = followService.addFollow(toUsername, fromUsername);
        if(result) {
            ProfileDto profileDto = profileService.getProfile(toUsername, fromUsername);
            return new ResponseEntity<>(profileDto, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/unfollow/{toUsername}/{fromUsername}")
    public ResponseEntity<?> unFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        Boolean result = followService.unFollow(toUsername, fromUsername);
        if(result) {
            ProfileDto profileDto = profileService.getProfile(toUsername, fromUsername);
            return new ResponseEntity<>(profileDto, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/follower/{profileUsername}/{requestingUsername}")
    public ResponseEntity<?> getFollower(@PathVariable String profileUsername, @PathVariable String requestingUsername){
        List<UserSimpleInfoDto> followerList = followService.getFollowerList(profileUsername, requestingUsername);
        return new ResponseEntity<>(followerList, HttpStatus.OK);
    }

    @GetMapping("/following/{profileUsername}/{requestingUsername}")
    public ResponseEntity<?> getFollowing(@PathVariable String profileUsername, @PathVariable String requestingUsername){
        List<UserSimpleInfoDto> followingList = followService.getFollowingList(profileUsername, requestingUsername);
        return new ResponseEntity<>(followingList, HttpStatus.OK);
    }
}
