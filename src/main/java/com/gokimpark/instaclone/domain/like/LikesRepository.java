package com.gokimpark.instaclone.domain.like;

import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllByPost(Post post);

    Long countAllByPost(Post post);

    void deleteAllByPost(Post post);
    void deleteByPostAndUser(Post post, User user);
}
