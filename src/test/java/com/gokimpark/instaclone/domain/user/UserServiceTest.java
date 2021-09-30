package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.user.dto.EditDto;
import com.gokimpark.instaclone.web.user.dto.JoinDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    @Transactional
    public void createAccount() throws Exception {
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("abc@gmail.com");
        joinDto.setName("kim");
        joinDto.setUsername("id123");
        joinDto.setPassword("pass1234");

        UserDto savedUser = userService.createAccount(joinDto);

        assertEquals(savedUser.getId(), userRepository.findByUsername("id123").get().getId());
    }

    @Test(expected = UserException.class)
    public void login() throws Exception {
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setName("kim");
        user1.setUsername("id123");
        user1.setPassword("pass1234");

        User user2 = new User();
        user2.setEmail("efg@gmail.com");
        user2.setName("lee");
        user2.setUsername("id456");
        user2.setPassword("pass5678");

        userRepository.save(user1);
        userRepository.save(user2);

        Optional<User> findUser = userRepository.findByUsername("id456");
        UserDto login = userService.login("id456", "pass56778");

        if(findUser.isPresent()){
            assertEquals(findUser.get().getUsername(), login.getUsername());
            assertEquals(findUser.get().getPassword(), login.getPassword());
        }
    }

    @Test
    @Transactional
    public void update() throws Exception {
        User user = new User();
        user.setEmail("abc@gmail.com");
        user.setName("kim");
        user.setUsername("id123");
        user.setPassword("pass1234");

        User savedUser = userRepository.save(user);
        EditDto editDto = new EditDto();
        editDto.setUserId(savedUser.getId());
        editDto.setName("Lee");
        editDto.setUsername("id789");
        editDto.setEmail("abc@naver.com");
        UserDto updateProfile = userService.updateProfile(editDto);

        assertEquals("id789", updateProfile.getUsername());
    }
}