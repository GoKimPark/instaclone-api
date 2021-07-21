package com.gokimpark.instaclone.post;

import com.gokimpark.instaclone.member.Member;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    @ResponseBody
    @GetMapping("/p/{postId}/")
    public Post findByPost(@PathVariable Long postId){
        return postService.findByPostId(postId);
    }

    @PostMapping
    public Member save(Post post){
         postService.save(post);
         return post.getMember();
    }

    @DeleteMapping("/p/{postId}/")
    public String delete(@PathVariable Long postId){
        postService.delete(postId);
        return "redirect:/{userId}/";
    }
}
