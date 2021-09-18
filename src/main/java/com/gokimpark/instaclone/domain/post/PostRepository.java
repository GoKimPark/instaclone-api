package com.gokimpark.instaclone.domain.post;
import com.gokimpark.instaclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUser(User user);
    void deleteAllByUser(User user);

    Long countAllByUser(User user);
}
