package com.gokimpark.instaclone.member;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{username}/")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public Member ShowProfile(@PathVariable String username){
        return memberService.findByUsername(username);
    }
}
