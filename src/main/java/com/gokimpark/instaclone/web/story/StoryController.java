package com.gokimpark.instaclone.web.story;

import com.gokimpark.instaclone.domain.story.Story;
import com.gokimpark.instaclone.domain.story.StoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stories")
public class StoryController {
    private StoryService storyService;

    @PostMapping
    public void addStory(@RequestBody Story story, String username){
        storyService.save(story, username);
    }

    @GetMapping("/{userId}/{storyId}/")
    public Story findByStoryId (@PathVariable String userId, @PathVariable String storyId){
        return storyService.findByStoryId(storyId);
    }

    @DeleteMapping
    public String deleteStory(@RequestBody String storyId){
        storyService.delete(storyId);
        return "redirect:/{userId}/";
    }
}
