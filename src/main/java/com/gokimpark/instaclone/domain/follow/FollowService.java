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

    public Boolean addFollow(String toUsername, String fromUsername){

        User toUser = userRepository.findByUsername(toUsername).orElseThrow(UserException::new);
        User fromUser = userRepository.findByUsername(fromUsername).orElseThrow(UserException::new);

        Optional<Follow> relation = followRepository.findByToUserAndFromUser(toUser.getId(), fromUser.getId());
        if(relation.isPresent()) return false;
        followRepository.save(new Follow(toUser.getId(),  fromUser.getId()));
        return true;
    }

    public Boolean unFollow(String toUsername, String fromUsername) {

        User toUser = userRepository.findByUsername(toUsername).orElseThrow(UserException::new);
        User fromUser = userRepository.findByUsername(fromUsername).orElseThrow(UserException::new);

        Optional<Follow> relation = followRepository.findByToUserAndFromUser(toUser.getId(), fromUser.getId());
        if(relation.isEmpty()) return false;
        followRepository.delete(relation.get());
        return true;
    }

    public List<FollowSimpleListDto> getFollowingList(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        List<User> followingList = followRepository.findAllByFromUser(user.getId());
        return followingList.stream()
                .map(FollowSimpleListDto::new)
                .collect(Collectors.toList());
    }

    public List<FollowSimpleListDto> getFollowerList(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        List<User> followerList = followRepository.findAllByToUser(user.getId());
        return followerList.stream()
                .map(FollowSimpleListDto::new)
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
