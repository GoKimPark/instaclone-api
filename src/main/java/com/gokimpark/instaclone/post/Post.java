package com.gokimpark.instaclone.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
    @Id @GeneratedValue
    private Long PostId;

    @Column(name = "post_owner_id")
    private String UserId;

    private String PostPhoto;
    private String caption;
    // tag
    private String location;

    public Post(){}
}
