package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.exception.FollowException;
import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.user.dto.UserSimpleInfoDto;
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

    public List<UserSimpleInfoDto> getFollowerList(String username, String requestingUsername){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        User requestingUser = userRepository.findByUsername(requestingUsername).orElseThrow(UserException::new);

        List<UserSimpleInfoDto>  usersWhoFollowedUser = followRepository.findAllByToUser(user.getId());
        if(username.equals(requestingUsername)) {
            for(UserSimpleInfoDto userInfo : usersWhoFollowedUser) {
                userInfo.setRequestingUsername(username);
                User targetUser = userRepository.findByUsername(userInfo.getUsername()).orElseThrow(UserException::new);
                setFollowStatus(userInfo, targetUser.getId(), user.getId());
            }
        }
        else {
            for(UserSimpleInfoDto userInfo : usersWhoFollowedUser) {
                userInfo.setRequestingUsername(requestingUsername);
                User targetUser = userRepository.findByUsername(userInfo.getUsername()).orElseThrow(UserException::new);
                setFollowStatus(userInfo, targetUser.getId(), requestingUser.getId());
            }
        }
        return usersWhoFollowedUser;
    }

    public List<UserSimpleInfoDto> getFollowingList(String username, String requestingUsername) {
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        User requestingUser = userRepository.findByUsername(requestingUsername).orElseThrow(UserException::new);

        List<UserSimpleInfoDto> usersFollowedByUser = followRepository.findAllByFromUser(user.getId());
        if(username.equals(requestingUsername)) {
            for(UserSimpleInfoDto userInfo : usersFollowedByUser) {
                userInfo.setRequestingUsername(username);
                userInfo.setFollowStatus(FollowStatus.FOLLOWING);
            }
        }
        else {
            for(UserSimpleInfoDto userInfo : usersFollowedByUser) {
                userInfo.setRequestingUsername(requestingUsername);
                User targetUser = userRepository.findByUsername(userInfo.getUsername()).orElseThrow(UserException::new);
                setFollowStatus(userInfo, targetUser.getId(), requestingUser.getId());
            }
        }
        return usersFollowedByUser;
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

    public boolean isFollowingById(Long toUserId, Long fromUserId) {
        return followRepository.findByToUserAndFromUser(toUserId, fromUserId).isPresent();
    }

    public boolean isFollowingByUsername(String toUsername, String fromUsername) {
        User toUser = userRepository.findByUsername(toUsername).orElseThrow(UserException::new);
        User fromUser = userRepository.findByUsername(fromUsername).orElseThrow(UserException::new);
        return isFollowingById(toUser.getId(), fromUser.getId());
    }

    public void setFollowStatus(UserSimpleInfoDto userInfo, Long toUserId, Long fromUserId) {
        if(toUserId.equals(fromUserId))
            userInfo.setFollowStatus(FollowStatus.ONESELF);
        else if(isFollowingById(toUserId, fromUserId))
            userInfo.setFollowStatus(FollowStatus.FOLLOWING);
        else
            userInfo.setFollowStatus(FollowStatus.UNFOLLOW);
    }
}
