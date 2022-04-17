package com.gokimpark.instaclone.web.user;

import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
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
@RequestMapping("/api/account")
public class AccountApiController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody @Validated JoinDto joinDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(joinDto, HttpStatus.BAD_REQUEST);
        }
        if(userService.existByEmail(joinDto.getEmail())) return new ResponseEntity<>(joinDto, HttpStatus.BAD_REQUEST);
        if(userService.existByUsername(joinDto.getUsername()))  return new ResponseEntity<>(joinDto, HttpStatus.BAD_REQUEST);

        UserDto user = userService.createAccount(joinDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(loginDto, HttpStatus.BAD_REQUEST);
        }
        UserDto user = userService.login(loginDto.getLoginId(), loginDto.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteAccount(@PathVariable String username){

        userService.deleteAccount(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
