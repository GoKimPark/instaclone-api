package com.gokimpark.instaclone.account;

import com.gokimpark.instaclone.member.MemberService;
import com.gokimpark.instaclone.member.Member;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final MemberService memberService;
    public AccountController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/emailsignup/")
    public String SignUp(){
        return "signUp";
    }

    @PostMapping("/emailsignup/")
    public Member SignUp(@RequestBody AccountJoinForm joinForm){
        return memberService.signUp(joinForm);
    }

    @PostMapping("/login/")
    public Member Login(@RequestBody AccountLoginForm loginForm){
        return memberService.Login(loginForm);
    }

}
