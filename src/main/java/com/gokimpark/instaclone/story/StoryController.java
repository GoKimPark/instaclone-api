package com.gokimpark.instaclone.story;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stories")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @ResponseBody
    @GetMapping("/{UserId}/{StoryId}/")
    public Story findOnByStoryId (@PathVariable String UserId, @PathVariable Long StoryId){
        return storyService.findOneByStoryId(StoryId);
    }


    @PostMapping("/{UserId}/{StoryId}/")
    public String processShowStory(@PathVariable String UserId, @PathVariable Long StoryId, Model model) {
        Story story = storyService.findOneByStoryId(StoryId);
        model.addAttribute(story);
        return "redirect:/stories/{UserId}/{StoryId}/";
    }
}
