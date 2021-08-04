package com.gokimpark.instaclone.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gokimpark.instaclone.web.member.JoinDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @Column(unique = true)
    private String email;

    private String profileImageUrl;

    private String name;

    @Column(unique = true)
    private String username; // userId

    private String website;

    @Lob
    private String bio;

    private Long postCnt;
    private Long followerCnt;
    private Long followingCnt;

    @Column(unique = true)
    private String phoneNumber;
    private String gender;

    @JsonIgnore
    private String password;

    public Member(JoinDto joinDto) {
        this.username = joinDto.getUsername();
        this.email = joinDto.getJoinId();
        this.name = joinDto.getName();
        this.password = joinDto.getPassword();

        this.postCnt = 0L;
        this.followerCnt = 0L;
        this.followingCnt = 0L;
    }

    // set
    public void postCountInc(){
        this.postCnt++;
    }

    public void postCountDec(){
        this.postCnt--;
    }
}
