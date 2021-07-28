package com.gokimpark.instaclone.account;

import com.gokimpark.instaclone.member.MemberService;
import com.gokimpark.instaclone.member.Member;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public Object SignUp(@RequestBody @Validated AccountJoinForm joinForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
        }
        return memberService.signUp(joinForm);
    }

    @PostMapping("/login/")
    public Object Login(@RequestBody @Validated AccountLoginForm loginForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
        }
        return memberService.Login(loginForm);
    }

    @GetMapping("/edit/")
    public AccountEditDto getProfileEdit(@RequestBody Member member){
        return memberService.findEditInfo(member);
    }

    @PostMapping("/edit/")
    public Object setProfileEdit (@RequestBody String memberPK, @RequestBody @Validated AccountEditDto editInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
        }
        return memberService.editProfile(memberPK, editInfo);
    }
}
