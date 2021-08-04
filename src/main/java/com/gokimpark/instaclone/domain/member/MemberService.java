package com.gokimpark.instaclone.domain.member;

import com.gokimpark.instaclone.web.member.EditDto;
import com.gokimpark.instaclone.web.member.JoinDto;
import com.gokimpark.instaclone.web.member.LoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    ModelMapper mapper = new ModelMapper();

    public Member signUp(JoinDto joinDto) {
        Member member = new Member(joinDto);
        return memberRepository.save(member);
    }

    public Member findMemberById(String id){
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(null);
    }

    public Member Login(LoginDto loginDto){
        // email, id 한번에 받는 것 처리
        return findMemberById(loginDto.getLoginId());
        //return memberRepository.findByEmailOrUsernameOrPhoneNumber(email, userName, phoneNumber);
    }
    public Member findByEmail(String email) {
        return memberRepository.findById(email).get();
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public EditDto findEditInfo(String id) {
        Member member = findMemberById(id);
        return mapper.map(member, EditDto.class);
    }

    public EditDto modifyProfile(String memberPK, EditDto editInfo) {
        Member member = memberRepository.findById(memberPK).get();

        member.setName(editInfo.getName());
        member.setId(editInfo.getEmail());
        member.setWebsite(editInfo.getWebSite());
        member.setBio(editInfo.getBio());
        member.setPhoneNumber(editInfo.getPhoneNumber());
        member.setGender(editInfo.getGender());

        return editInfo;
    }
}
