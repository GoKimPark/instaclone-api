package com.gokimpark.instaclone.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{userId}/")
public class MemberController {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @ResponseBody
    @GetMapping
    public Member ShowProfile(@PathVariable String UserId){
        Member member = this.memberRepository.findById(UserId);
        return member;
    }
}
