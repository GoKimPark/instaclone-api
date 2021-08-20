package com.gokimpark.instaclone.web.likes;

import com.gokimpark.instaclone.domain.like.Likes;
import com.gokimpark.instaclone.domain.like.LikesService;
import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/{postId}/{username}")
    public ResponseEntity<?> addLikes(@PathVariable Long postId, @PathVariable String username){
        User user = userService.findByUsername(username);
        Post post = postService.findByPostId(postId);

        likesService.addLike(Likes.builder().post(post).user(user).build());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{username}")
    public ResponseEntity<?> deleteLikes(@PathVariable Long postId, @PathVariable String username){
        User user = userService.findByUsername(username);
        Post post = postService.findByPostId(postId);
        likesService.unLike(post, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
