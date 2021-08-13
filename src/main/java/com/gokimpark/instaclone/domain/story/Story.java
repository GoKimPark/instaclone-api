package com.gokimpark.instaclone.domain.story;

import com.gokimpark.instaclone.domain.image.Image;
import com.gokimpark.instaclone.domain.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@NoArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Image imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stories")
    private User user;
}
