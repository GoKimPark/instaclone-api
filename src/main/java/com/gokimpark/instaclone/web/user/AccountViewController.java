package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.exception.AccountException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String createAccount(@Validated @ModelAttribute JoinDto joinDto, BindingResult bindingResult,
                                HttpServletRequest httpServletRequest) {
        if(bindingResult.hasErrors()) {
            return "account/create";
        }
        try {
            UserDto user = userService.createAccount(joinDto);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("username", user.getUsername());
        } catch (AccountException e) {
            bindingResult.reject("duplicate.joinDto.username", e.getMessage());
            return "account/create";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "account/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginDto loginDto, BindingResult bindingResult,
                        HttpServletRequest httpServletRequest) {
        if(bindingResult.hasErrors()) {
            return "account/login";
        }
        try {
            UserDto user = userService.login(loginDto.getLoginId(), loginDto.getPassword());
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("username", user.getUsername());
        } catch (AccountException e) {
            bindingResult.reject("mismatch", e.getMessage());
            return "account/login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
