package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.user.dto.EditDto;
import com.gokimpark.instaclone.web.user.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowService followService;
    private final PostService postService;
    ModelMapper mapper = new ModelMapper();

    public UserDto createAccount(JoinDto joinDto) {

        User user = User.builder()
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .username(joinDto.getUsername())
                .password(joinDto.getPassword())
                .build();

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDto.class);
    }

    public UserDto login(String loginId, String password){

        User user = userRepository.findByEmailOrUsername(loginId, loginId).orElseThrow(UserException::new);
        if(!user.isEqualPassword(password)) throw new UserException("잘못된 비밀번호 입니다.");
        return mapper.map(user, UserDto.class);
    }

    public UserDto findById(Integer userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) throw new UserException();
        return mapper.map(user, UserDto.class);
    }

    public UserDto findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UserException();
        return mapper.map(user, UserDto.class);
    }

    public UserDto findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new UserException();
        return mapper.map(user, UserDto.class);
    }

    public Boolean existByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    public Boolean existByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Transactional
    public UserDto updateProfile(EditDto editDto) {
        Optional<User> user = userRepository.findById(editDto.getUserId());
        if(user.isEmpty()) throw new UserException();

        user.get().update(editDto.getName(), editDto.getUsername(),
                editDto.getWebSite(), editDto.getBio(),
                editDto.getEmail(), editDto.getPhoneNumber()
        );

        Optional<User> editedUser = userRepository.findById(editDto.getUserId());
        if(editedUser.isEmpty()) throw new UserException();
        return mapper.map(editedUser, UserDto.class);
    }

    public void deleteAccount(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            followService.deleteFollowRelation(user.get().getId());
            followService.deleteFollowRelation(user.get().getId());
            postService.deleteAllByUser(user.get());
        }
    }
}
