package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.exception.FollowException;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.domain.user.dto.UserSimpleInfoDto;
import com.gokimpark.instaclone.web.user.dto.JoinDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FollowServiceTest {

    @Autowired UserService userService;
    @Autowired FollowService followService;

    @Test
    public void duplicateFollow() {
        List<UserDto> users = createUsers();
        UserDto user1 = users.get(0);
        UserDto user2 = users.get(1);

        followService.addFollow(user1.getUsername(), user2.getUsername());
        assertEquals(1, followService.getFollowerCount(user1.getUsername()));
        assertThrows(FollowException.class, () -> followService.addFollow(user1.getUsername(), user2.getUsername()));
    }

    @Test
    public void unfollow() {
        List<UserDto> users = createUsers();
        UserDto user1 = users.get(0);
        UserDto user2 = users.get(1);

        followService.addFollow(user1.getUsername(), user2.getUsername());
        followService.unFollow(user1.getUsername(), user2.getUsername());
        Assertions.assertEquals(0, followService.getFollowingCount(user2.getUsername()));
        assertThrows(FollowException.class, () -> followService.unFollow(user1.getUsername(), user2.getUsername()));
    }

    @Test
    public void differentUsers() {
        List<UserDto> users = createUsers();
        UserDto user1 = users.get(0);
        UserDto user2 = users.get(1);

        assertThrows(FollowException.class, () -> followService.addFollow(user1.getUsername(), user1.getUsername()));
        assertThrows(FollowException.class, () -> followService.unFollow(user2.getUsername(), user2.getUsername()));
    }

    @Test
    public void getFollowingList() {
        List<UserDto> users = createUsers();
        UserDto user1 = users.get(0);
        UserDto user2 = users.get(1);
        UserDto user3 = users.get(2);

        followService.addFollow(user2.getUsername(), user1.getUsername());
        followService.addFollow(user3.getUsername(), user1.getUsername());

        List<UserSimpleInfoDto> followingList = followService.getFollowingList(user1.getUsername(), user1.getUsername());
        assertEquals(2, followingList.size());
    }

    @Test
    public void getFollowerList() {
        List<UserDto> users = createUsers();
        UserDto user1 = users.get(0);
        UserDto user2 = users.get(1);
        UserDto user3 = users.get(2);

        followService.addFollow(user1.getUsername(), user2.getUsername());
        followService.addFollow(user1.getUsername(), user3.getUsername());

        List<UserSimpleInfoDto> followerList = followService.getFollowerList(user1.getUsername(), user1.getUsername());
        assertEquals(2, followerList.size());
    }

    public List<UserDto> createUsers()  {
        List<UserDto> users = new ArrayList<>();

        JoinDto joinDto1 = new JoinDto();
        joinDto1.setEmail("abc123@gmail.com");
        joinDto1.setName("kim");
        joinDto1.setUsername("id123");
        joinDto1.setPassword("pass1234");
        users.add(userService.createAccount(joinDto1));

        JoinDto joinDto2 = new JoinDto();
        joinDto2.setEmail("kiwi456@gmail.com");
        joinDto2.setName("Lee");
        joinDto2.setUsername("id456");
        joinDto2.setPassword("pass4567");
        users.add(userService.createAccount(joinDto2));

        JoinDto joinDto3 = new JoinDto();
        joinDto3.setEmail("kaya1234@gmail.com");
        joinDto3.setName("kaya");
        joinDto3.setUsername("id789");
        joinDto3.setPassword("pass7890");
        users.add(userService.createAccount(joinDto3));
        return users;
    }
}