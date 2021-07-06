package com.gokimpark.instaclone.story;

import com.gokimpark.instaclone.member.Member;
import com.gokimpark.instaclone.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stories")
public class StoryController {
    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;

    @ResponseBody
    @GetMapping("/{UserId}/{StoryId}/")
    public Model showStory(@PathVariable String UserId, @PathVariable String StoryId, Model model){
        return model;
    }


    @PostMapping("/{UserId}/{StoryId}/")
    public String processShowStory(@PathVariable String UserId, @PathVariable String StoryId, Model model) {
        Member member = this.memberRepository.findById(UserId);
        Story story = storyRepository.getStoryById(StoryId);
        model.addAttribute(story);
        return "redirect:/stories/{UserId}/{StoryId}/";
    }
}
