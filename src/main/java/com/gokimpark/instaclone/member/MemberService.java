package com.gokimpark.instaclone.member;

import com.gokimpark.instaclone.account.AccountEditDto;
import com.gokimpark.instaclone.account.AccountJoinForm;
import com.gokimpark.instaclone.account.AccountLoginForm;
import com.gokimpark.instaclone.profile.ProfileMemberInfoDto;
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
        return memberRepository.findById(loginForm.getEmail()).get();
    }

    public Member findByUserId(String id) {
        return memberRepository.findByUserId(id);
    }

    public AccountEditDto findEditInfo(Member member) {
        Member editMember = memberRepository.findById(member.getEmail()).get();
        return mapper.map(editMember, AccountEditDto.class);
    }

    public AccountEditDto editProfile(String memberPK, AccountEditDto editInfo) {
        Member member = memberRepository.findById(memberPK).get();

        member.setId(editInfo.getId());
        member.setEmail(editInfo.getEmail());
        member.setWebsite(editInfo.getWebSite());
        member.setBio(editInfo.getBio());
        member.setEmail(editInfo.getEmail());
        member.setPhoneNumber(editInfo.getPhoneNumber());
        member.setGender(editInfo.getGender());

        return editInfo;
    }
}
