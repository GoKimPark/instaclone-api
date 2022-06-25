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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        User requestingUser;
        if(requestingUsername.equals(username)) requestingUser = user;
        else requestingUser = userRepository.findByUsername(requestingUsername).orElseThrow(UserException::new);

        List<UserSimpleInfoDto>  usersWhoFollowedUser = followRepository.findAllByToUser(user.getId());
        if(username.equals(requestingUsername)) {
            Set<String> usernameFollowedByUser = getAllUsernameFollowedByUser(user.getId());
            for(UserSimpleInfoDto userInfo : usersWhoFollowedUser) {
                setFollowStatus(userInfo.getUsername(), user.getUsername(), usernameFollowedByUser, userInfo);
            }
        }
        else {
            Set<String> usernameFollowedByRequestingUser = getAllUsernameFollowedByUser(requestingUser.getId());
            for(UserSimpleInfoDto userInfo : usersWhoFollowedUser) {
                userInfo.setRequestingUsername(requestingUsername);
                setFollowStatus(userInfo.getUsername(), requestingUser.getUsername(), usernameFollowedByRequestingUser, userInfo);
            }
        }
        return usersWhoFollowedUser;
    }

    public List<UserSimpleInfoDto> getFollowingList(String username, String requestingUsername) {
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        User requestingUser;
        if(requestingUsername.equals(username)) requestingUser = user;
        else requestingUser = userRepository.findByUsername(requestingUsername).orElseThrow(UserException::new);

        List<UserSimpleInfoDto> usersFollowedByUser = followRepository.findAllByFromUser(user.getId());
        if(username.equals(requestingUsername)) {
            for(UserSimpleInfoDto userInfo : usersFollowedByUser) {
                userInfo.setRequestingUsername(username);
                userInfo.setFollowStatus(FollowStatus.FOLLOWING);
            }
        }
        else {
            Set<String> usernameFollowedByRequestingUser = getAllUsernameFollowedByUser(requestingUser.getId());
            for(UserSimpleInfoDto userInfo : usersFollowedByUser) {
                userInfo.setRequestingUsername(requestingUsername);
                setFollowStatus(userInfo.getUsername(), requestingUsername, usernameFollowedByRequestingUser, userInfo);
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

    public void setFollowStatus(String targetUsername, String requestingUsername, Set<String> usernameFollowedByRequestingUser, UserSimpleInfoDto userInfo) {
        if(requestingUsername.equals(targetUsername))
            userInfo.setFollowStatus(FollowStatus.ONESELF);
        else if(usernameFollowedByRequestingUser.contains(targetUsername))
            userInfo.setFollowStatus(FollowStatus.FOLLOWING);
        else
            userInfo.setFollowStatus(FollowStatus.UNFOLLOW);
    }

    public Set<String> getAllUsernameFollowedByUser(Long userId) {
        List<UserSimpleInfoDto> followRelationList = followRepository.findAllByFromUser(userId);

        Set<String> usernameSet = new HashSet<>();
        for(UserSimpleInfoDto userSimpleInfo : followRelationList) {
            usernameSet.add(userSimpleInfo.getUsername());
        }
        return usernameSet;
    }

}
