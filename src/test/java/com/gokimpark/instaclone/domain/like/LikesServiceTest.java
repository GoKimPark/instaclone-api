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

        JoinDto joinDto = new JoinDto();
        joinDto.setEmail("abc@gmail.com");
        joinDto.setName("kim");
        joinDto.setUsername("id123");
        joinDto.setPassword("pass1234");
        UserDto userAccount = userService.createAccount(joinDto);

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setUsername(userAccount.getUsername());
        postCreateDto.setImageUrl("imageUrl-123");
        postCreateDto.setCaption("caption");
        postCreateDto.setLocation("seoul");
        PostDetailDto createdPost = postService.create(postCreateDto);
        
        Long count = likesService.countByPost(createdPost.getId());
        assertEquals(0L, count);

        likesService.addLike(createdPost.getId(), userAccount.getUsername());
        Long count2 = likesService.countByPost(createdPost.getId());
        assertEquals(1L, count2);

        likesService.unLike(createdPost.getId(), userAccount.getUsername());
        Long count3 = likesService.countByPost(createdPost.getId());
        assertEquals(0L, count3);
    }
}