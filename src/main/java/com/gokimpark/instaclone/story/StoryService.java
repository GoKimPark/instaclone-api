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
    public List<Story> findAllByUsername(String username){
        return storyRepository.findAllByUsername(username);
    }
    public Story findOneByStoryId(Long id){
        return storyRepository.findById(id).get();
    }
}
