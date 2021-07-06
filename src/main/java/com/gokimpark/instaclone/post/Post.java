package com.gokimpark.instaclone.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Post {
    @Id
    @Column(name = "USER_ID")
    private String User_Id;

    private Long PostId;
    private String PostPhoto;
    private String caption;
    // tag
    private String location;
}
