package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.user.dto.JoinDto;
import com.gokimpark.instaclone.web.user.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountViewController {

    private final UserService userService;

    @GetMapping("/create")
    public String createAccount(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "account/create";
    }

    @PostMapping("/create")
    public String createAccount(@Validated @ModelAttribute JoinDto joinDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "account/create";
        }
        UserDto user = userService.createAccount(joinDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "account/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "account/login";
        }
        UserDto user = userService.login(loginDto.getLoginId(), loginDto.getPassword());
        return "redirect:/";
    }
}
