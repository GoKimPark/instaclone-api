package com.gokimpark.instaclone.domain.like;

import com.gokimpark.instaclone.domain.exception.PostException;
import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.post.PostRepository;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void addLike(Integer postId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostException::new);
        Likes newLike = Likes.builder()
                .post(post)
                .user(user).build();
        likesRepository.save(newLike);
    }

    public void unLike(Integer postId, String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostException::new);
        likesRepository.deleteByPostAndUser(post, user);
    }

    public Long countByPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostException::new);
        return likesRepository.countAllByPost(post);
    }
}
