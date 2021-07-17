package com.gokimpark.instaclone.member;

import com.gokimpark.instaclone.account.AccountJoinForm;
import com.gokimpark.instaclone.account.AccountLoginForm;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    ModelMapper mapper = new ModelMapper();

    public Member signUp(AccountJoinForm joinForm) {
        return memberRepository.save(new Member(joinForm));
    }

    public Member Login(AccountLoginForm loginForm){
        return memberRepository.findById(loginForm.getEmail()).get();
    }

    public Member findByUserId(String id) {
        return memberRepository.findByUserId(id);
    }
}
