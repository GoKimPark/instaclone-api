package com.gokimpark.instaclone.post;

import com.gokimpark.instaclone.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/{UserId}/p/{PostId}/")
public class PostController {
    private final PostRepository postRepository;

    @ResponseBody
    @GetMapping
    public Post showPost(@PathVariable String UserId, @PathVariable String PostId){
        return this.postRepository.getPostById(PostId);
    }

    @PostMapping
    public String AddComment(@PathVariable String UserId,
                             @PathVariable String PostId,
                             @RequestBody String comment){
        Comment createdComment = new Comment(UserId, comment);
        Post post = this.postRepository.getPostById(PostId);
        post.AddComment(createdComment);
        return "redirect:/{UserId}/p/{PostId}/";
    }

    @DeleteMapping
    public String DeletePost(@PathVariable String UserId, @PathVariable String PostId){
        this.postRepository.delete(PostId);
        return "redirect:/{userId}/";
    }
}
