package com.gokimpark.instaclone.post;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<Long, Post> store = new HashMap<>();
    private Long sequence = 0L;

    public Post getPostById(String PostId){
        return store.get(PostId);
    }
    public void save(Post post){
        post.setPostId(++sequence);
        store.put(post.getPostId(), post);
    }
    public void delete(String PostId){
        store.remove(PostId);
    }
}
