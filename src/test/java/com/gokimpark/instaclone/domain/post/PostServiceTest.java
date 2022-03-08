package com.gokimpark.instaclone.domain.post;

import com.gokimpark.instaclone.domain.post.dto.PostDetailDto;
import com.gokimpark.instaclone.domain.user.UserService;
import com.gokimpark.instaclone.domain.user.dto.UserDto;
import com.gokimpark.instaclone.web.post.dto.PostCreateDto;
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
public class PostServiceTest {

    @Autowired UserService userService;
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

    @Test
    @Transactional
    public void create() {
        UserDto user = createUser();

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setUsername(user.getUsername());
        postCreateDto.setImageUrl("imageUrl-123");
        postCreateDto.setCaption("caption");
        postCreateDto.setLocation("seoul");

        PostDetailDto createdPost = postService.create(postCreateDto);
        Assertions.assertEquals(postCreateDto.getLocation(), createdPost.getLocation());
    }

    @Test
    @Transactional
    public void update() {
        UserDto user = createUser();

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setUsername(user.getUsername());
        postCreateDto.setImageUrl("imageUrl-1234");
        postCreateDto.setCaption("caption");
        postCreateDto.setLocation("seoul");

        PostDetailDto createdPost = postService.create(postCreateDto);
        PostDetailDto updatePost = postService.update(createdPost.getId(), "caption123", "pusan");
        assertEquals("pusan", updatePost.getLocation());

        PostDetailDto findPost = postService.findByPostId(createdPost.getId());
        assertEquals(updatePost.getId(), findPost.getId());
        assertEquals("pusan", findPost.getLocation());
    }

    private UserDto createUser() {
        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("abc@gmail.com");
        joinDto.setName("kim");
        joinDto.setUsername("id123");
        joinDto.setPassword("pass1234");
        return userService.createAccount(joinDto);
    }
}