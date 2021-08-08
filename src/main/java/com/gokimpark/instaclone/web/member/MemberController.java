package com.gokimpark.instaclone.web.member;

import com.gokimpark.instaclone.domain.member.Member;
import com.gokimpark.instaclone.domain.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/accounts/emailsignup/")
    public String SignUp(@ModelAttribute("joinDto") JoinDto joinDto){
        return "account/join";
    }

    @PostMapping("/accounts/emailsignup/")
    public Object SignUp(@RequestBody @Validated JoinDto joinDto, BindingResult bindingResult){
        System.out.println(joinDto.toString());
        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors();
            //return "account/join";
        }
        memberService.signUp(joinDto);
        return "loginHome";
    }

    @GetMapping("/accounts/login/")
    public String Login(@ModelAttribute("loginDto") LoginDto loginDto){
        return "account/login";
    }

    @PostMapping("/accounts/login/")
    public Object Login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
           //return "account/login";
            return bindingResult.getAllErrors();
        }

        memberService.Login(loginDto, bindingResult);
        if(bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
           //return "account/login";
        }
        return "loginHome";
    }

    @GetMapping("/accounts/edit/")
    public String getProfileEdit(String id, Model model){

        EditDto editInfo = memberService.findEditInfo(id);
        model.addAttribute(editInfo);

        return "member/profileInfoEdit";
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
