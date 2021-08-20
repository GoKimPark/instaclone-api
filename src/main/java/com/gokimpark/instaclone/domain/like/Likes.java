package com.gokimpark.instaclone.domain.like;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"})
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Likes(Post post, User user){
        this.post = post;
        this.user = user;
    }
}
