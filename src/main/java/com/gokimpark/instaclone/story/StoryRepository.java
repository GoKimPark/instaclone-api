package com.gokimpark.instaclone.story;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StoryRepository {
    private final Map<Long, Story> store = new HashMap<>();
    private Long sequence = 0L;

    public Story getStoryById(String StoryId){
        return store.get(StoryId);
    }
    public void save(Story story){
        story.setStoryId(++sequence);
        store.put(story.getStoryId(), story);
    }
}
