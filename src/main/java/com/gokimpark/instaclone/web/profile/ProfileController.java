package com.gokimpark.instaclone.web.profile;

import com.gokimpark.instaclone.domain.profile.ProfileService;
import com.gokimpark.instaclone.domain.story.Story;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{username}")
    public ProfileDto findProfile(@PathVariable String username) {
        return profileService.findProfile(username);
    }

    @GetMapping("/stories/highlights/{storyId}/")
    public Story findStoryByStoryId(@PathVariable String storyId){
        return profileService.findStoryByStoryId(storyId);
    }
}
