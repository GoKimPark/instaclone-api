package com.gokimpark.instaclone.domain.story;

import com.gokimpark.instaclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findAllByUser(User user);
    void deleteAllByUser(User user);
}
