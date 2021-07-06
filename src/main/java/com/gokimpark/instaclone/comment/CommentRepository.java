package com.gokimpark.instaclone.comment;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public interface CommentRepository extends Repository<Comment, String> {
    @Transactional(readOnly = true)
    @Cacheable("comments")
    Collection<Comment> findById(@Param("id") String id);

    void save(String PostId, Comment comment);
    void delete(String postId, String commentId);
}
