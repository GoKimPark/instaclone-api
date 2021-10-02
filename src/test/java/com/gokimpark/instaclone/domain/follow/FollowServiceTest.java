package com.gokimpark.instaclone.domain.follow;

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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FollowServiceTest {

    @Autowired UserService userService;
    @Autowired FollowService followService;

    @Test
    public void DuplicateCheck() {
        createUser();
        UserDto user1 = userService.findByUsername("id123");
        UserDto user2 = userService.findByUsername("id456");

        followService.addFollow(user1.getUsername(), user2.getUsername());
        Assertions.assertEquals(1, followService.getFollowerCount(user1.getUsername()));

        followService.addFollow(user1.getUsername(), user2.getUsername());
        Assertions.assertEquals(1, followService.getFollowerCount(user1.getUsername()));
    }

    @Test
    public void UnfollowCheck() {
        createUser();
        UserDto user1 = userService.findByUsername("id123");
        UserDto user2 = userService.findByUsername("id456");

        followService.addFollow(user1.getUsername(), user2.getUsername());
        Assertions.assertEquals(1, followService.getFollowingCount(user2.getUsername()));

        followService.unFollow(user1.getUsername(), user2.getUsername());
        Assertions.assertEquals(0, followService.getFollowingCount(user2.getUsername()));
        Assertions.assertEquals(0, followService.getFollowerCount(user1.getUsername()));
    }

    private void createUser() {
        JoinDto joinDto1 = new JoinDto();
        joinDto1.setEmail("abc123@gmail.com");
        joinDto1.setName("kim");
        joinDto1.setUsername("id123");
        joinDto1.setPassword("pass1234");

        JoinDto joinDto2 = new JoinDto();
        joinDto2.setEmail("abc456@gmail.com");
        joinDto2.setName("Lee");
        joinDto2.setUsername("id456");
        joinDto2.setPassword("pass4567");

        JoinDto joinDto3 = new JoinDto();
        joinDto3.setEmail("abc789@gmail.com");
        joinDto3.setName("Park");
        joinDto3.setUsername("id789");
        joinDto3.setPassword("pass7890");

        UserDto account1 = userService.createAccount(joinDto1);
        UserDto account2 = userService.createAccount(joinDto2);
        UserDto account3 = userService.createAccount(joinDto3);
    }
}