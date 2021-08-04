package com.gokimpark.instaclone.domain.comment;

import com.gokimpark.instaclone.domain.post.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String userId;

    @Lob
    private String comment;

    @LastModifiedDate
    private LocalDateTime CreatedTime;
}
