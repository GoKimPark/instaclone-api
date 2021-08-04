package com.gokimpark.instaclone.domain.story;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, String> {
    List<Story> findAllByUsername(String username);
}
