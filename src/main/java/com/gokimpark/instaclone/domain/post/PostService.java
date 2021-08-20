package com.gokimpark.instaclone.domain.post;

import com.gokimpark.instaclone.domain.comment.CommentRepository;
import com.gokimpark.instaclone.domain.like.LikesRepository;
import com.gokimpark.instaclone.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;

    public List<Post> findAllByUser(User user){
        return postRepository.findAllByUser(user);
    }

    public String findCountPost(User user){
        return String.valueOf(postRepository.countAllByUser(user));
    }

    public Post findByPostId(Long postId){
        return postRepository.findById(postId).get();
    }

    public Post create(Post post){
        return postRepository.save(post);
    }

    public Post update(Long postId, String caption, String location) {
        Optional<Post> post = postRepository.findById(postId);
        if(post == null) return null;

        post.get().update(caption, location);
        return post.get();
    }

    public void delete(Long postId){
        Post post = postRepository.findById(postId).get();
        postRepository.delete(post);
        likesRepository.deleteAllByPost(post);
        commentRepository.deleteAllByPost(post);
    }

    public void deleteAllByUser(User user) {
        postRepository.deleteAllByUser(user);
    }
}
