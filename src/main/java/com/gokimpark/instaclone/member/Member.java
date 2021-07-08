package com.gokimpark.instaclone.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gokimpark.instaclone.account.MemberJoinInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @Column(name = "member_email", nullable = false)
    private String email;

    private String ProfilePhoto;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String id;

    private String Website;
    private String bio;

    @Column(nullable = false)
    private String PhoneNumber;
    private String gender;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public Member(){}
    public Member(MemberJoinInfo member){
        this.email = member.getEmail();
        this.PhoneNumber = member.getPhoneNumber();
        this.id = member.getUserId();
        this.name = member.getUserName();
        this.password = member.getPassword();
    }


}
