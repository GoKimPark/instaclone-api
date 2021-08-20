package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.web.user.dto.JoinDto;
import com.gokimpark.instaclone.web.user.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    @PostMapping("/emailsignup")
    public ResponseEntity<?> createAccount(@RequestBody @Validated JoinDto joinDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(joinDto, HttpStatus.BAD_REQUEST);
        }
        if(userService.existByEmail(joinDto.getEmail()) != null) return new ResponseEntity<>(joinDto, HttpStatus.BAD_REQUEST);
        if(userService.existByUsername(joinDto.getUsername()) != null)  return new ResponseEntity<>(joinDto, HttpStatus.BAD_REQUEST);


        User createdAccount = userService.createAccount(User.builder()
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .username(joinDto.getUsername())
                .password(joinDto.getPassword())
                .build());

        User user = userService.createAccount(createdAccount);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(loginDto, HttpStatus.BAD_REQUEST);
        }
        User user = userService.login(loginDto.getLoginId(), loginDto.getPassword());
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteAccount(@PathVariable String username){

        userService.deleteAccount(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
