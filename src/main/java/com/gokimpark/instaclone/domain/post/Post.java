package com.gokimpark.instaclone.domain.post;

import com.gokimpark.instaclone.domain.comment.Comment;
import com.gokimpark.instaclone.domain.like.Likes;
import com.gokimpark.instaclone.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Likes> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    private String caption;
    private String location;

    @Builder
    public Post(User user, String imageUrl, String caption, String location){
        this.user = user;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.location = location;
    }

    public void update(String caption, String location){
        this.caption = caption;
        this.location = location;
    }
}
