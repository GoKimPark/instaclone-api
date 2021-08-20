package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.profile.ProfileService;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.web.user.dto.EditDto;
import com.gokimpark.instaclone.web.user.dto.ProfileDto;
import com.gokimpark.instaclone.web.user.dto.ProfilePostDto;
import com.gokimpark.instaclone.web.user.dto.ProfileUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final FollowService followService;
    private final ProfileService profileService;

    ModelMapper mapper = new ModelMapper();

    @GetMapping("/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username) {

        User user = userService.findByUsername(username);
        ProfileUserInfoDto userInfoDto = mapper.map(user, ProfileUserInfoDto.class);

        List<ProfilePostDto> profilePosts = profileService.getProfilePosts(user);
        userInfoDto.setPostCnt(String.valueOf(profilePosts.size()));

        userInfoDto.setFollowerCnt(followService.getFollowerCount(user));
        userInfoDto.setFollowingCnt(followService.getFollowingCount(user));

        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserInfo(userInfoDto);
        profileDto.setPosts(profilePosts);

        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @GetMapping("/edit/{username}")
    public ResponseEntity<?> getProfileEditDto(@PathVariable String username){
        User user = userService.findByUsername(username);
        EditDto editDto = mapper.map(user, EditDto.class);
        return new ResponseEntity<>(editDto, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateProfile(@RequestBody @Validated EditDto editDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(editDto, HttpStatus.BAD_REQUEST);
        }
        User user = userService.updateProfile(editDto);
        return new ResponseEntity<>(editDto, HttpStatus.OK);
    }
}
