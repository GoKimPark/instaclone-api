package com.gokimpark.instaclone.follow;

import com.gokimpark.instaclone.member.Member;

import javax.persistence.*;

@Entity
public class Following {

    @Id @Column(name = "following_member_email")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Member UserId;

    public Following(){}
}
