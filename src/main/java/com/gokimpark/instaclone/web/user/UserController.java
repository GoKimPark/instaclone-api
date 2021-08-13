package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/accounts/emailsignup/")
    public Object createMember(@RequestBody @Validated JoinDto joinDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return userService.create(joinDto).getId();
    }

    @PostMapping("/accounts/login/")
    public Object login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return userService.login(loginDto).getId();
    }
}
