package com.gokimpark.instaclone.domain.member;

import com.gokimpark.instaclone.web.member.EditDto;
import com.gokimpark.instaclone.web.member.JoinDto;
import com.gokimpark.instaclone.web.member.LoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    ModelMapper mapper = new ModelMapper();

    public Member signUp(JoinDto joinDto) {
        Member member = mapper.map(joinDto, Member.class);
        return memberRepository.save(member);
    }

    public Member Login(LoginDto loginDto){
        // email, id 한번에 받는 것 처리
        return memberRepository.findById(loginDto.getLoginId()).get();
        //return memberRepository.findByEmailOrUsernameOrPhoneNumber(email, userName, phoneNumber);
    }
    public Member findByEmail(String email) {
        return memberRepository.findById(email).get();
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public EditDto findEditInfo(Member member) {
        Member editMember = memberRepository.findById(member.getEmail()).get();
        return mapper.map(editMember, EditDto.class);
    }

    public EditDto modifyProfile(String memberPK, EditDto editInfo) {
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
