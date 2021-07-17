package com.gokimpark.instaclone.story;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoryService {
    private final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Transactional
    public Story save(Story story){
        return storyRepository.save(story);
    }
    public List<Story> findAllByUserId(String id){
        return storyRepository.findAllByUserId(id);
    }
    public Story findOneByStoryId(Long id){
        return storyRepository.findById(id).get();
    }
}
