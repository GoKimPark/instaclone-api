package com.gokimpark.instaclone.domain.like;

import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.post.dto.PostDetailDto;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.post.dto.PostCreateDto;
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
public class LikesServiceTest {

    @Autowired LikesService likesService;
    @Autowired LikesRepository likesRepository;
    @Autowired UserService userService;
    @Autowired PostService postService;

    @Test
    @Transactional
    public void checkLike() {
        PostDetailDto post = createPost();
        assertEquals(0L, likesService.countByPost(post.getId()));

        UserDto user = createUser();
        likesService.addLike(post.getId(), user.getUsername());
        assertEquals(1L, likesService.countByPost(post.getId()));

        likesService.unLike(post.getId(), user.getUsername());
        assertEquals(0L, likesService.countByPost(post.getId()));
    }

    private UserDto createUser() {
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("abc@gmail.com");
        joinDto.setName("kim");
        joinDto.setUsername("id123");
        joinDto.setPassword("pass1234");
        return userService.createAccount(joinDto);
    }

    private PostDetailDto createPost() {
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("kiwi456@gmail.com");
        joinDto.setName("lee");
        joinDto.setUsername("id456");
        joinDto.setPassword("pass456");
        UserDto user = userService.createAccount(joinDto);

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setUsername(user.getUsername());
        postCreateDto.setImageUrl("imageUrl-123");
        postCreateDto.setCaption("caption");
        postCreateDto.setLocation("seoul");
        return postService.create(postCreateDto);
    }
}