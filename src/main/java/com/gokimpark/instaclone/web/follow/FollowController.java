package com.gokimpark.instaclone.web.follow;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.follow.dto.UserSimpleInfoDto;
import com.gokimpark.instaclone.domain.user.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final ProfileService profileService;

    @PostMapping("/follow/{toUsername}/{fromUsername}")
    public String addFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        Boolean result = followService.addFollow(toUsername, fromUsername);
        return "redirect:/profile/{toUsername}/{fromUsername}";
    }

    @PostMapping("/unfollow/{toUsername}/{fromUsername}")
    public String unFollow(@PathVariable String toUsername, @PathVariable String fromUsername){
        Boolean result = followService.unFollow(toUsername, fromUsername);
        return "redirect:/profile/{toUsername}/{fromUsername}";
    }

    @GetMapping("/follower/{fromUsername}/{requestedUsername}")
    public String getFollower(@PathVariable String fromUsername, @PathVariable String requestedUsername, Model model){
        List<UserSimpleInfoDto> followerList = followService.getFollowRelationList("follower", fromUsername, requestedUsername);
        model.addAttribute("users", followerList);
        return "/account/userList";
    }

    @GetMapping("/following/{fromUsername}/{requestedUsername}")
    public String getFollowing(@PathVariable String fromUsername, @PathVariable String requestedUsername, Model model){
        List<UserSimpleInfoDto> followingList = followService.getFollowRelationList("following", fromUsername, requestedUsername);
        model.addAttribute("users", followingList);
        return "/account/userList";
    }
}
