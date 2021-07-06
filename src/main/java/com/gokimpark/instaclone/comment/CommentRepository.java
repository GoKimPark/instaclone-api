package com.gokimpark.instaclone.comment;

import com.gokimpark.instaclone.comment.Comment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CommentRepository {
    private final Map<Date, Comment> store = new HashMap<>();

    public List<Comment> getComment(){
        return new ArrayList<>(store.values());
    }
    public void save(Comment comment){
        store.put(comment.getCreatedTime(), comment);
    }
}
