package com.gokimpark.instaclone.web.post;

import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.post.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private PostService postService;

    @GetMapping("/p/{postId}/")
    public Post getPost(@PathVariable String postId){
        return postService.findByPostId(postId);
    }

    @PostMapping
    public void addPost(@RequestBody Post post, String username){
         postService.save(post, username);
    }

    @DeleteMapping
    public String deletePost(@RequestBody String postId){
        postService.delete(postId);
        return "redirect:/{userId}/";
    }
}
