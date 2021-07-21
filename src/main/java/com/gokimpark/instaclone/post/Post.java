package com.gokimpark.instaclone.post;

import com.gokimpark.instaclone.image.Image;
import com.gokimpark.instaclone.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue
    @Column(unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member member;

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
   //  private Set<Image> images;
}
