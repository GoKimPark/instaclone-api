package com.gokimpark.instaclone.domain.story;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoryService {

    private StoryRepository storyRepository;

    @Transactional
    public Story save(Story story, String username){
        story.setUsername(username);
        return storyRepository.save(story);
    }

    public List<Story> findAllByUsername(String username){
        return storyRepository.findAllByUsername(username);
    }

    public Story findByStoryId(String id){
        return storyRepository.findById(id).get();
    }

    public void delete(String postId) {
        storyRepository.deleteById(postId);
    }
}
