package com.gokimpark.instaclone.web.post;

import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.post.dto.PostDetailDto;
import com.gokimpark.instaclone.web.post.dto.PostCreateDto;
import com.gokimpark.instaclone.web.post.dto.PostEditDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody @Validated PostCreateDto postCreateDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(postCreateDto, HttpStatus.BAD_REQUEST);
        }
        PostDetailDto savedPost = postService.create(postCreateDto);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@RequestBody PostEditDto postEditDto) {
        PostDetailDto post = postService.update(postEditDto.getPostId(), postEditDto.getCaption(), postEditDto.getLocation());
        if(post == null){
            return new ResponseEntity<>(postEditDto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Integer postId){
        PostDetailDto post = postService.findByPostId(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId){
        postService.delete(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
