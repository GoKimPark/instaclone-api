package com.gokimpark.instaclone.post;

import com.gokimpark.instaclone.image.Image;
import com.gokimpark.instaclone.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id", unique = true)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
   //  private Set<Image> images;
}
