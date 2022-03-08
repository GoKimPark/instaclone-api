package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.exception.FollowException;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
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
        return users;
    }
}