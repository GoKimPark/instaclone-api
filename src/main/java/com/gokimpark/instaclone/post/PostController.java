package com.gokimpark.instaclone.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{UserId}/p/{PostId}/")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    @ResponseBody
    @GetMapping("/p/{PostId}/")
    public Post findOnePost(@PathVariable Long PostId){
        return postService.findOneByPostId(PostId);
    }

    @PostMapping


    @DeleteMapping
    public String delete(@PathVariable Long postId){
        postService.delete(postId);
        return "redirect:/{userId}/";
    }
}
