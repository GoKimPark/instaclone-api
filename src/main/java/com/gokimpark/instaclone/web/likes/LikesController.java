package com.gokimpark.instaclone.web.likes;

import com.gokimpark.instaclone.domain.like.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/{postId}/{username}")
    public ResponseEntity<?> addLikes(@PathVariable Integer postId, @PathVariable String username){

        likesService.addLike(postId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{username}")
    public ResponseEntity<?> deleteLikes(@PathVariable Integer postId, @PathVariable String username){

        likesService.unLike(postId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
