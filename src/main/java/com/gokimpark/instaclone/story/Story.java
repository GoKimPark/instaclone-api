package com.gokimpark.instaclone.story;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Story {
    @Id
    @Column(name = "USER_EMAIL")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime StoryId;
    private String StoryPhoto;

}
