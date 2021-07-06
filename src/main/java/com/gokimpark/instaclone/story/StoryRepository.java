package com.gokimpark.instaclone.story;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StoryRepository extends Repository<Story, String> {
    @Query("select story from Story story where story.StoryId =:id")
    @Transactional(readOnly = true)
    Story getStoryById(@Param("id") String StoryId);

    void save(Story story);
    void delete(String id);
}
