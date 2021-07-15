package com.gokimpark.instaclone.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.member.id = :id")
    @Transactional(readOnly = true)
    List<Post> findAllByUserId(String id);

}
