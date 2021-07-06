package com.gokimpark.instaclone.follow;

import com.gokimpark.instaclone.member.Member;

import javax.persistence.*;

@Entity
public class Follower {

    @Id
    @Column(name = "USER_EMAIL")
    @ManyToOne
    @JoinColumn(name = "email")
    private Member UserId;
}
