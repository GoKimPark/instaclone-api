package com.gokimpark.instaclone.post;

import com.gokimpark.instaclone.comment.Comment;
import com.gokimpark.instaclone.comment.CommentRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

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
