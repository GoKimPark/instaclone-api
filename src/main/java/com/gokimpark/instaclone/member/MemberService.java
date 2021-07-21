package com.gokimpark.instaclone.member;

import com.gokimpark.instaclone.account.AccountEditDto;
import com.gokimpark.instaclone.account.AccountJoinForm;
import com.gokimpark.instaclone.account.AccountLoginForm;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        // email, id 한번에 받는 것 처리
        String email ="";
        String userName ="";
        String phoneNumber="";
        return memberRepository.findByEmailOrUsernameOrPhoneNumber(email, userName, phoneNumber);
    }
    public Member findByUserEmail(String email) {
        return memberRepository.findById(email).get();
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public AccountEditDto findEditInfo(Member member) {
        Member editMember = memberRepository.findById(member.getEmail()).get();
        return mapper.map(editMember, AccountEditDto.class);
    }

    public AccountEditDto editProfile(String memberPK, AccountEditDto editInfo) {
        Member member = memberRepository.findById(memberPK).get();

        member.setName(editInfo.getName());
        member.setEmail(editInfo.getEmail());
        member.setWebsite(editInfo.getWebSite());
        member.setBio(editInfo.getBio());
        member.setEmail(editInfo.getEmail());
        member.setPhoneNumber(editInfo.getPhoneNumber());
        member.setGender(editInfo.getGender());

        return editInfo;
    }
}
