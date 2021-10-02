package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.follow.dto.FollowSimpleListDto;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public void addFollow(String toUsername, String fromUsername){

        User toUser = userRepository.findByUsername(toUsername).orElseThrow(UserException::new);
        User fromUser = userRepository.findByUsername(fromUsername).orElseThrow(UserException::new);

        try{
            followRepository.save(Follow.builder()
                    .toUser(toUser.getId())
                    .fromUser(fromUser.getId())
                    .build());
        } catch (Exception e){
            throw new UserException("이미 팔로우함.");
        }
    }

    public void unFollow(String toUsername, String fromUsername) {

        User toUser = userRepository.findByUsername(toUsername).orElseThrow(UserException::new);
        User fromUser = userRepository.findByUsername(fromUsername).orElseThrow(UserException::new);

        Follow follow = followRepository.findByToUserAndFromUser(toUser.getId(), fromUser.getId()).orElseThrow(UserException::new);
        followRepository.delete(follow);
    }

    public List<FollowSimpleListDto> getFollowingList(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        List<User> followingList = followRepository.findAllByFromUser(user.getId());
        return followingList.stream()
                .map(User -> new FollowSimpleListDto(User))
                .collect(Collectors.toList());
    }

    public List<FollowSimpleListDto> getFollowerList(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        List<User> followerList = followRepository.findAllByToUser(user.getId());
        return followerList.stream()
                .map(User -> new FollowSimpleListDto(User))
                .collect(Collectors.toList());
    }

    public Long getFollowingCount(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        return followRepository.countByFromUser(user.getId());
    }

    public Long getFollowerCount(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        return followRepository.countByToUser(user.getId());
    }

    public Pair<Long, Long> getProfileFollowCount(String username){
        return Pair.of(getFollowerCount(username), getFollowingCount(username));
    }

    public void deleteFollowRelation(Integer userId){
        followRepository.deleteAllByFromUser(userId);
        followRepository.deleteAllByToUser(userId);
    }
}
