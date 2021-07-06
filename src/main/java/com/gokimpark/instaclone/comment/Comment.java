package com.gokimpark.instaclone.comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "USER_ID")
    private String UserId;

    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime CreatedTime;
}
