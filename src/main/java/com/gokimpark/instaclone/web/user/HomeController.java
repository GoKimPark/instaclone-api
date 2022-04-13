package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(@SessionAttribute(required = false) String username, Model model) {
        if(username == null) {
            return "loginHome";
        }
        UserDto user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "userHome";
    }
}
