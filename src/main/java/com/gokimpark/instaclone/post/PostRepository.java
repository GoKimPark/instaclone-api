package com.gokimpark.instaclone.post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends Repository<Post, String> {
    @Query("select post from Post post where post.PostId =:id")
    @Transactional(readOnly = true)
    Post getPostById(@Param("id") String PostId);

    void save(Post post);
    void delete(String Id);
}
