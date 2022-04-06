package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.user.ProfileService;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.user.dto.EditDto;
import com.gokimpark.instaclone.domain.user.dto.ProfileDto;
import com.gokimpark.instaclone.web.user.dto.ProfileRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/REST/profile")
public class ProfileRestController {

    private final UserService userService;
    private final ProfileService profileService;

    ModelMapper mapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<?> getProfile(@RequestBody @Validated ProfileRequestDto profileRequestDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(profileRequestDto, HttpStatus.BAD_REQUEST);

        ProfileDto profileDto = profileService.getProfile(profileRequestDto.getTargetUsername(), profileRequestDto.getRequestedUsername());
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
