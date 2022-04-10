package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.user.ProfileService;
import com.gokimpark.instaclone.domain.user.dto.ProfileDto;
import com.gokimpark.instaclone.domain.user.dto.ProfileUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileViewController {

    private final ProfileService profileService;

    @GetMapping("/{toUsername}/{fromUsername}")
    public String getProfile(@PathVariable String toUsername, @PathVariable String fromUsername, Model model) {
        ProfileDto profileDto = profileService.getProfile(toUsername, fromUsername);
        model.addAttribute("profileDto", profileDto);
        return "account/profile";
    }

    @GetMapping("/users/{username}")
    public String showAllUserSimpleProfile(@PathVariable String username, Model model) {
        List<ProfileUserInfoDto> users = profileService.getAllProfile(username);
        model.addAttribute("users", users);
        return "account/userList";
    }
}
