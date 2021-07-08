package com.gokimpark.instaclone.follow;

import com.gokimpark.instaclone.member.Member;

import javax.persistence.*;

@Entity
public class Follower {
    @Id @Column(name = "follower_member_email")
    @ManyToOne @JoinColumn(name = "email")
    private Member UserId;

    public Follower(){}
}
