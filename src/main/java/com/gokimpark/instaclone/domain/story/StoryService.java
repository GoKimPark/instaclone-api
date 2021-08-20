package com.gokimpark.instaclone.domain.story;

import com.gokimpark.instaclone.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;

    @Transactional
    public User createStory(User user, String imageUrl){

        Story saved = storyRepository.save(Story.builder()
                .imageUrl(imageUrl)
                .user(user)
                .build());

        return user;
    }

    public List<Story> findAllByUsername(User user){
        return storyRepository.findAllByUser(user);
    }

    public Optional<Story> findByStoryId(Long id){
        return storyRepository.findById(id);
    }

    public void delete(Long postId) {
        storyRepository.deleteById(postId);
    }

    public void deleteAllByUser(User user) {
        storyRepository.deleteAllByUser(user);
    }
}
