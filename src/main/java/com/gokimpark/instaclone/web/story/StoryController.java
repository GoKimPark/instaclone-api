package com.gokimpark.instaclone.web.story;

import com.gokimpark.instaclone.domain.story.Story;
import com.gokimpark.instaclone.domain.story.StoryService;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/story")
public class StoryController {

    private final StoryService storyService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createStory(@RequestBody @Validated StoryDto storyDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(storyDto, HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUsername(storyDto.getUsername());
        User saved = storyService.createStory(user, storyDto.getImageUrl());
        if(saved == null) { return new ResponseEntity<>(storyDto, HttpStatus.BAD_GATEWAY);}
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}/{storyId}/")
    public ResponseEntity<?> findByStoryId (@PathVariable String username, @PathVariable Long storyId){
        Optional<Story> story = storyService.findByStoryId(storyId);
        return new ResponseEntity<>(story.get().getImageUrl(), HttpStatus.OK);
    }

    @DeleteMapping("/{storyId}")
    public ResponseEntity<?> deleteStory(@PathVariable Long storyId){
        storyService.delete(storyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
