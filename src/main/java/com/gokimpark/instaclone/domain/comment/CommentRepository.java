package com.gokimpark.instaclone.domain.comment;

import com.gokimpark.instaclone.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
    void deleteAllByPost(Post post);
}
