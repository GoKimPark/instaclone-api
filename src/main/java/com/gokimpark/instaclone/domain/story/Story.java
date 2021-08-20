package com.gokimpark.instaclone.domain.story;

import com.gokimpark.instaclone.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @OneToOne(fetch = FetchType.LAZY)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stories")
    private User user;

    @Builder
    public Story(String imageUrl, User user){
        this.imageUrl = imageUrl;
        this.user = user;
    }
}
