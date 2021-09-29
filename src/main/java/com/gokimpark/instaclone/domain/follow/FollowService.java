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

        Optional<User> toUser = userRepository.findByUsername(toUsername);
        Optional<User> fromUser = userRepository.findByUsername(fromUsername);

        if(toUser.isEmpty() || fromUser.isEmpty()) throw new UserException();

        try{
            followRepository.save(Follow.builder()
                    .toUser(toUser.get().getId())
                    .fromUser(fromUser.get().getId())
                    .build());
        } catch (Exception e){
            throw new UserException("이미 팔로우함.");
        }
    }

    public void unFollow(String toUsername, String fromUsername){

        Optional<User> toUser = userRepository.findByUsername(toUsername);
        Optional<User> fromUser = userRepository.findByUsername(fromUsername);

        if(toUser.isEmpty()) throw new UserException();
        if(fromUser.isEmpty()) throw new UserException();

        Optional<Follow> follow = followRepository.findByToUserAndFromUser(toUser.get().getId(), fromUser.get().getId());
        follow.ifPresent(followRepository::delete);
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
