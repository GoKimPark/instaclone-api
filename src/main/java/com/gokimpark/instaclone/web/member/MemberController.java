package com.gokimpark.instaclone.web.member;

import com.gokimpark.instaclone.domain.member.Member;
import com.gokimpark.instaclone.domain.member.MemberService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private MemberService memberService;

    @PostMapping("/accounts/emailsignup/")
    public Object SignUp(@RequestBody @Validated JoinDto joinDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
        }
        return memberService.signUp(joinDto);
    }

    @PostMapping("/accounts/login/")
    public Object Login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
        }
        return memberService.Login(loginDto);
    }

    @GetMapping("/accounts/edit/")
    public EditDto getProfileEdit(@RequestBody Member member){
        return memberService.findEditInfo(member);
    }

    @PostMapping("/accounts/edit/")
    public Object setProfileEdit (@RequestBody String memberPK, @RequestBody @Validated EditDto editInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
        }
        return memberService.modifyProfile(memberPK, editInfo);
    }

    @PostMapping("/{username}/")
    public Member findProfile(@PathVariable String username){
        return memberService.findByUsername(username);
    }
}
