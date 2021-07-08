package com.gokimpark.instaclone.comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment_writer_id")
    private String UserId;

    private String comment;

    private LocalDateTime CreatedTime;

    public Comment(){}
    public Comment(String UserId, Comment comment){}
}
