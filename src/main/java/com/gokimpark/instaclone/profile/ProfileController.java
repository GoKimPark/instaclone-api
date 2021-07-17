package com.gokimpark.instaclone.profile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    ProfileDto getProfile(@PathVariable String userId) {
        return profileService.getProfile(userId);
        //return ProfileDto.from(profileService.getProfile(userId));
    }
}
