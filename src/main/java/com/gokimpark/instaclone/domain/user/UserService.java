package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.follow.FollowService;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.web.user.dto.EditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowService followService;
    private final PostService postService;

    public User createAccount(User user) {
        return userRepository.save(user);
    }

    public User login(String loginId, String password){

        User user = userRepository.findByEmailOrUsername(loginId, loginId).orElseThrow(() -> new UserException());
        if(!user.isEqualPassword(password)) throw new UserException("잘못된 비밀번호 입니다.");
        return user;
    }

    public User findById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserException());
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserException());
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException());
    }

    public User existByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public User existByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public User updateProfile(EditDto editDto) {
        User user = findById(editDto.getUserId());

        user.update(editDto.getName(), editDto.getUsername(),
                editDto.getWebSite(), editDto.getBio(),
                editDto.getEmail(), editDto.getPhoneNumber()
        );
        return findById(editDto.getUserId());
    }

    public void deleteAccount(String username){
        User user = findByUsername(username);
        followService.deleteFollowRelation(user.getId());
        followService.deleteFollowRelation(user.getId());
        postService.deleteAllByUser(user);
    }
}
