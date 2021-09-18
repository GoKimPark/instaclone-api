package com.gokimpark.instaclone.web.post;

import com.gokimpark.instaclone.domain.like.LikesService;
import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final LikesService likesService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody @Validated PostCreateDto postCreateDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(postCreateDto, HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUsername(postCreateDto.getUsername());

        Post post = Post.builder()
                .imageUrl(postCreateDto.getImageUrl())
                .caption(postCreateDto.getCaption())
                .location(postCreateDto.getLocation())
                .user(user)
                .build();

        Post saved = postService.create(post);
        return new ResponseEntity<>(postCreateDto.getUsername(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@RequestBody PostEditDto postEditDto) {
        Post post = postService.update(postEditDto.getPostId(), postEditDto.getCaption(), postEditDto.getLocation());
        if(post == null){
            return new ResponseEntity<>(postEditDto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(post.getUser().getUsername(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Integer postId){
        Post post = postService.findByPostId(postId);

        PostShowDto postShowDto = PostShowDto.builder()
                .postId(post.getId())
                .username(post.getUser().getName())
                .imageUrl(post.getImageUrl())
                .likesCount(String.valueOf(likesService.countByPost(post))).build();

        return new ResponseEntity<>(postShowDto, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId){
        postService.delete(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
