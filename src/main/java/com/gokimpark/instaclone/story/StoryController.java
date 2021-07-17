package com.gokimpark.instaclone.story;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stories")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/{userId}/{storyId}/")
    public Story findByStoryId (@PathVariable String userId, @PathVariable Long storyId){
        return storyService.findOneByStoryId(storyId);
    }
}
