package com.gokimpark.instaclone.domain.post;

import com.gokimpark.instaclone.domain.comment.Comment;
import com.gokimpark.instaclone.domain.image.Image;
import com.gokimpark.instaclone.domain.like.Like;
import com.gokimpark.instaclone.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    private String caption;
    private String location;

    @Builder
    public Post(User user, List<Image> images, String caption, String location){
        this.user = user;
        this.images = images;
        this.caption = caption;
        this.location = location;
    }

    public void update(String caption, String location){
        this.caption = caption;
        this.location = location;
    }
}
