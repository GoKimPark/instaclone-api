package com.gokimpark.instaclone.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{userId}")
    ProfileDto readProfile(@PathVariable("userId") String userId) {
        return ProfileDto.from(profileService.getProfile(userId));
    }
}
