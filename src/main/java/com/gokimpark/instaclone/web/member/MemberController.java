package com.gokimpark.instaclone.web.member;

import com.gokimpark.instaclone.domain.member.Member;
import com.gokimpark.instaclone.domain.member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/accounts/emailsignup/")
    public Object SignUp(@RequestBody @Validated JoinDto joinDto, BindingResult bindingResult){

        System.out.println(joinDto.toString());

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
        Member member = memberService.Login(loginDto);
        System.out.println(member.toString());
        return member;
    }

    @GetMapping("/accounts/edit/")
    public EditDto getProfileEdit(@RequestBody String id){
        System.out.println(id);
        return memberService.findEditInfo(id);
    }

    @PostMapping("/accounts/edit/")
    public Object setProfileEdit (@RequestBody String memberId, @RequestBody @Validated EditDto editInfo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
        }
        return memberService.modifyProfile(memberId, editInfo);
    }

    @PostMapping("/{username}/")
    public Member findProfile(@PathVariable String username){
        return memberService.findByUsername(username);
    }
}
