package com.gokimpark.instaclone.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{userId}/")
public class MemberController {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public Member ShowProfile(@PathVariable String UserId){
        Member member = this.memberRepository.findById(UserId);
        return member;
    }
}
