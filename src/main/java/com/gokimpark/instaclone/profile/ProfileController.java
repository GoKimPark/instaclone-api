package com.gokimpark.instaclone.profile;

import com.gokimpark.instaclone.story.Story;
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
    public ProfileDto findProfile(@PathVariable String userId) {
        return profileService.findProfile(userId);
    }

    @GetMapping("/stories/highlights/{storyId}/")
    public Story findStoryByStoryId(@PathVariable Long storyId){
        return profileService.findStoryByStoryId(storyId);
    }
}
