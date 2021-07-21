package com.gokimpark.instaclone.story;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findAllByUsername(String username);
}
