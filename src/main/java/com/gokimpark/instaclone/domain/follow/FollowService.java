package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.exception.FollowException;
import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.follow.dto.FollowSimpleListDto;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public Boolean addFollow(String toUsername, String fromUsername){
        checkSameUser(toUsername, fromUsername);

        User toUser = userRepository.findByUsername(toUsername).orElseThrow(UserException::new);
        User fromUser = userRepository.findByUsername(fromUsername).orElseThrow(UserException::new);

        Optional<Follow> relation = getFollowRelation(toUser.getId(), fromUser.getId());
        if(relation.isPresent())
            throw new FollowException("이미 follow 한 관계입니다.");
        followRepository.save(new Follow(toUser.getId(),  fromUser.getId()));
        return true;
    }

    public Boolean unFollow(String toUsername, String fromUsername) {
        checkSameUser(toUsername, fromUsername);

        User toUser = userRepository.findByUsername(toUsername).orElseThrow(UserException::new);
        User fromUser = userRepository.findByUsername(fromUsername).orElseThrow(UserException::new);

        Optional<Follow> relation = getFollowRelation(toUser.getId(), fromUser.getId());
        if(relation.isEmpty())
            throw new FollowException("follow 관계가 아닙니다.");
        followRepository.delete(relation.get());
        return true;
    }

    private Optional<Follow> getFollowRelation(Long toUserId, Long fromUserId) {
        return followRepository.findByToUserAndFromUser(toUserId, fromUserId);
    }

    private void checkSameUser(String toUsername, String fromUsername) {
        if(toUsername.equals(fromUsername)) throw new FollowException("follower 와 following 의 대상이 동일합니다.");
    }

    public List<FollowSimpleListDto> getFollowingList(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        return followRepository.findAllByFromUser(user.getId());
    }

    public List<FollowSimpleListDto> getFollowerList(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        return followRepository.findAllByToUser(user.getId());
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

    public void deleteFollowRelation(Long userId){
        followRepository.deleteAllByFromUser(userId);
        followRepository.deleteAllByToUser(userId);
    }

    public boolean isFollow(Long toUserId, Long fromUserId) {
        return followRepository.findByToUserAndFromUser(toUserId, fromUserId).isPresent();
    }
}
