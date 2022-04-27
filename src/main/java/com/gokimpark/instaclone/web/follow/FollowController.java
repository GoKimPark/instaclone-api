package com.gokimpark.instaclone.web.follow;

import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.user.dto.UserSimpleInfoDto;
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

    @GetMapping("/follower/{username}/{requestingUsername}")
    public String getFollower(@PathVariable String username, @PathVariable String requestingUsername, Model model){
        List<UserSimpleInfoDto> followerList = followService.getFollowerList(username, requestingUsername);
        model.addAttribute("users", followerList);
        return "/account/userList";
    }

    @GetMapping("/following/{username}/{requestingUsername}")
    public String getFollowing(@PathVariable String username, @PathVariable String requestingUsername, Model model){
        List<UserSimpleInfoDto> followingList = followService.getFollowingList(username, requestingUsername);
        model.addAttribute("users", followingList);
        return "/account/userList";
    }
}
