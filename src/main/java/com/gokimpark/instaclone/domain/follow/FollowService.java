package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public void addFollow(User toUser, User fromUser){

        try{
            followRepository.save(Follow.builder()
                    .toUser(toUser)
                    .fromUser(fromUser)
                    .build());
        } catch (Exception e){
            throw new UserException("이미 팔로우함.");
        }
    }

    public void unFollow(User toUser, User fromUser){
        followRepository.unFollow(toUser, fromUser);
    }

    public List<User> getFollowingList(User user){
        return followRepository.findAllByFromUser(user);
    }

    public String getFollowingCount(User user){
        Long count = followRepository.countByFromUser(user);
        return chgToStr(count);
    }

    public List<User> getFollowerList(User user){
        return followRepository.findAllByToUser(user);
    }

    public String getFollowerCount(User user){
        Long count = followRepository.countByToUser(user);
        return chgToStr(count);
    }

    public void deleteFollowRelation(User user){
        followRepository.deleteAllByFromUser(user);
        followRepository.deleteAllByToUser(user);
    }

    public String chgToStr(Long count){
        // 변경 예정
        return String.valueOf(count);
    }

}
