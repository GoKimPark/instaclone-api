package com.gokimpark.instaclone.story;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Story {

    @Id @GeneratedValue
    private Long StoryId;

    @Column(name = "story_owner_id")
    private String UserId;

    private String StoryPhoto;
}
