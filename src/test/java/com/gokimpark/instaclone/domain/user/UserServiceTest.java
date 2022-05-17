package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.exception.AccountException;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void createAccount() throws Exception {
        JoinDto joinDto = user1JoinDto();
        UserDto savedUser = userService.createAccount(joinDto);

        assertEquals(savedUser.getId(), userRepository.findByUsername("apple").get().getId());
    }

    @Test
    public void createAccountException() throws Exception {
        JoinDto user1JoinDto = user1JoinDto();
        JoinDto user2JoinDto = user2JoinDto();

        UserDto user1UserDto = userService.createAccount(user1JoinDto);
        assertEquals(user1UserDto.getUsername(), user1UserDto.getUsername());

        Assertions.assertThrows(AccountException.class,
                () -> userService.createAccount(user2JoinDto));
    }

    @Test
    public void login() throws Exception {
        JoinDto joinDto = user1JoinDto();
        UserDto user = userService.createAccount(joinDto);

        User findUser = userRepository.findByUsername("apple").orElseThrow(Exception::new);
        UserDto login = userService.login("apple", "pass1234");

        assertEquals(findUser.getUsername(), login.getUsername());
        assertEquals(findUser.getPassword(), login.getPassword());
    }

    @Test
    public void update() throws Exception {
        JoinDto joinDto = user1JoinDto();
        UserDto account = userService.createAccount(joinDto);

        EditDto editDto = new EditDto();
        editDto.setUserId(account.getId());
        editDto.setName("Lee");
        editDto.setUsername("kiwi");
        UserDto updateProfile = userService.updateProfile(editDto);

        assertEquals("kiwi", updateProfile.getUsername());
    }

    private static JoinDto user1JoinDto() {
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("apple@gmail.com");
        joinDto.setName("kim");
        joinDto.setUsername("apple");
        joinDto.setPassword("pass1234");
        return joinDto;
    }

    static JoinDto user2JoinDto() {
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("kiwi456@gmail.com");
        joinDto.setName("lee");
        joinDto.setUsername("apple");
        joinDto.setPassword("pass7890");
        return joinDto;
    }
}