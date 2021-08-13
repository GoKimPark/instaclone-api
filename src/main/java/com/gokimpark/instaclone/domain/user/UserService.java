package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.web.member.EditDto;
import com.gokimpark.instaclone.web.member.JoinDto;
import com.gokimpark.instaclone.web.member.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(JoinDto joinDto) {
        if(userRepository.findByEmail(joinDto.getEmail()) != null) throw new UserException("이미 존재하는 email 입니다.");
        if(userRepository.findByUsername(joinDto.getUsername()) != null) throw new UserException("이미 존재하는 id 입니다.");

        return userRepository.save(User.builder()
        .email(joinDto.getEmail())
        .name(joinDto.getName())
        .username(joinDto.getUsername())
        .password(joinDto.getPassword())
        .build());
    }

    public User findById(Long userId){
        Optional<User> member = userRepository.findById(userId);
        return member.orElse(null);
    }

    public User login(LoginDto loginDto){
        String id = loginDto.getLoginId();
        User findUser= userRepository.findByEmailOrUsername(id, id);
        if(findUser != null) throw new UserException("잘못된 아이디 입니다.");
        if(!findUser.isEqualPassword(loginDto.getPassword())) throw new UserException("잘못된 비밀번호 입니다.");
        return findUser;
    }

    public User updateProfile(EditDto editDto) {
        User user = findById(editDto.getUserId());

        user.update(editDto.getName(), editDto.getUsername(),
                editDto.getWebSite(), editDto.getBio(),
                editDto.getEmail(), editDto.getPhoneNumber()
        );
        return user;
    }
}
