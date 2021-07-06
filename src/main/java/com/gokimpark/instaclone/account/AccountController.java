package com.gokimpark.instaclone.account;

import com.gokimpark.instaclone.member.Member;
import com.gokimpark.instaclone.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final MemberRepository memberRepository;

    @Autowired
    public AccountController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping("/emailsignup/")
    public String SignUp(){
        return "signUp";
    }

    @PostMapping("/emailsignup/")
    public Member SignUp(@Valid @RequestBody MemberJoinInfo memberJoinInfo){
        Member member = memberRepository.save(memberJoinInfo);
        return member;
    }

    @PostMapping("/login/")
    public Member Login(@Valid @RequestBody MemberLoginInfo memberLoginInfo){
        Member member = memberRepository.findById(memberLoginInfo.getEmail());
        return member;
    }

}
