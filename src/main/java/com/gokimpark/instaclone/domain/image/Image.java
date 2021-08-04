package com.gokimpark.instaclone.domain.image;

import com.gokimpark.instaclone.domain.post.Post;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
