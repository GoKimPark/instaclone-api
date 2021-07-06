package com.gokimpark.instaclone.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gokimpark.instaclone.account.MemberJoinInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @Column(name = "USER_EMAIL")
    private String email;

    private String ProfilePhoto;
    private String UserName;
    private String UserId;

    private String Website;
    private String bio;

    private String PhoneNumber;
    private String gender;

    @JsonIgnore
    private String password;

    public Member(){}
    public Member(MemberJoinInfo member){
        this.email = member.getEmail();
        this.PhoneNumber = member.getPhoneNumber();
        this.UserId = member.getUserId();
        this.UserName = member.getUserName();
        this.password = member.getPassword();
    }


}
