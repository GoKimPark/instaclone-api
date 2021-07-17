package com.gokimpark.instaclone.image;

import com.gokimpark.instaclone.member.Member;
import com.gokimpark.instaclone.post.Post;
import com.gokimpark.instaclone.story.Story;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
