package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.post.dto.PostProfileDto;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.user.dto.EditDto;
import com.gokimpark.instaclone.web.user.dto.ProfileDto;
import com.gokimpark.instaclone.web.user.dto.ProfileUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
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
    private final PostService postService;

    ModelMapper mapper = new ModelMapper();

    @GetMapping("/{username}")
    public ResponseEntity<?> getProfile(@PathVariable String username) {

        UserDto user = userService.findByUsername(username);
        ProfileUserInfoDto userInfoDto = mapper.map(user, ProfileUserInfoDto.class);

        List<PostProfileDto> postProfileDtoList = postService.findAllProfilePostByUser(username);

        Pair<Long, Long> followCount = followService.getProfileFollowCount(username);
        userInfoDto.setFollowerCount(followCount.getFirst());
        userInfoDto.setFollowingCount(followCount.getSecond());

        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserInfo(userInfoDto);
        profileDto.setPosts(postProfileDtoList);

        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @GetMapping("/edit/{username}")
    public ResponseEntity<?> getProfileEditDto(@PathVariable String username){
        UserDto user = userService.findByUsername(username);
        EditDto editDto = mapper.map(user, EditDto.class);
        return new ResponseEntity<>(editDto, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateProfile(@RequestBody @Validated EditDto editDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(editDto, HttpStatus.BAD_REQUEST);
        }
        UserDto user = userService.updateProfile(editDto);
        EditDto edited = mapper.map(user, EditDto.class);
        return new ResponseEntity<>(edited, HttpStatus.OK);
    }


}
