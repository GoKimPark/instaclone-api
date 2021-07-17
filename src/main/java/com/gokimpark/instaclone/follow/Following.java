package com.gokimpark.instaclone.follow;

import com.gokimpark.instaclone.member.Member;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
@NoArgsConstructor
//@IdClass(Following.PK.class)
public class Following {

    /*
    @Id
    private String followerEmail;

    @Id
    private String followingEmail;

    @ManyToOne
    @JoinColumn(name = "followerEmail")
    private Member follower;

    @ManyToOne
    @JoinColumn(name = "followingEmail")
    private Member following;

    public static class PK implements Serializable {
        private String followerEmail;
        private String followingEmail;
    }*/
}
