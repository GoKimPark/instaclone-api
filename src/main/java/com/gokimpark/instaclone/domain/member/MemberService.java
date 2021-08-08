package com.gokimpark.instaclone.domain.member;

import com.gokimpark.instaclone.web.member.EditDto;
import com.gokimpark.instaclone.web.member.JoinDto;
import com.gokimpark.instaclone.web.member.LoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    ModelMapper mapper = new ModelMapper();

    public void signUp(JoinDto joinDto) {
        Member member = new Member(joinDto);
        memberRepository.save(member);
    }

    public Member findById(String id){
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(null);
    }

    public void Login(LoginDto loginDto, BindingResult bindingResult){
        Member member = findById(loginDto.getLoginId());

        /*
        if(isValidEmail(loginDto.getLoginId())) {
            member = findById(loginDto.getLoginId());
        }
        else member = findByUsername(loginDto.getLoginId());
         */

        if(member == null) bindingResult.rejectValue("loginId", "mismatch");
        else if (!member.isEqualPassword(loginDto.getPassword()))
            bindingResult.rejectValue("password", "mismatch");
    }

    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    public Member findByEmail(String email) {
        return memberRepository.findById(email).get();
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public EditDto findEditInfo(String id) {
        Member member = findById(id);
        return mapper.map(member, EditDto.class);
    }

    public EditDto modifyProfile(String memberId, EditDto editInfo) {
        Member member = findById(memberId);

        member.setName(editInfo.getName());
        member.setId(editInfo.getEmail());
        member.setWebsite(editInfo.getWebSite());
        member.setBio(editInfo.getBio());
        member.setPhoneNumber(editInfo.getPhoneNumber());
        member.setGender(editInfo.getGender());

        return editInfo;
    }
}
