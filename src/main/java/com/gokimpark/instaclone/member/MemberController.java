package com.gokimpark.instaclone.member;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{userId}/")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public Member ShowProfile(@PathVariable String userId){
        return memberService.findByUserId(userId);
    }
}
