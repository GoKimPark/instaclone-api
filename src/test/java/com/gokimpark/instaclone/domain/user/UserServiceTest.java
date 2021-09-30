package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.user.dto.EditDto;
import com.gokimpark.instaclone.web.user.dto.JoinDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @Transactional
    public void login() throws Exception {

        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("abc@gmail.com");
        joinDto.setName("kim");
        joinDto.setUsername("id123");
        joinDto.setPassword("pass1234");

        UserDto user = userService.createAccount(joinDto);

        User findUser = userRepository.findByUsername("id123").orElseThrow(Exception::new);
        UserDto login = userService.login("id123", "pass1234");

        assertEquals(findUser.getUsername(), login.getUsername());
        assertEquals(findUser.getPassword(), login.getPassword());
    }

    @Test
    @Transactional
    public void update() throws Exception {
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("abc@gmail.com");
        joinDto.setName("kim");
        joinDto.setUsername("id123");
        joinDto.setPassword("pass1234");

        UserDto account = userService.createAccount(joinDto);

        EditDto editDto = new EditDto();
        editDto.setUserId(account.getId());
        editDto.setName("Lee");
        editDto.setUsername("id789");
        editDto.setEmail("abc@naver.com");
        UserDto updateProfile = userService.updateProfile(editDto);

        assertEquals("id789", updateProfile.getUsername());
    }
}