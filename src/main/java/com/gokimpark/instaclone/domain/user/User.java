package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @Column(nullable = false)
    private String email;
    private String name;

    @Column(unique = true)
    private String username; // userId

    private String password;

    private String profileImageUrl;
    private String website;
    @Lob
    private String bio;

    @Column(unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @Builder
    public User(String email, String name, String username, String password) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Transactional
    public void update(String name, String username, String website, String bio, String email, String phoneNumber){
        this.name = name;
        this.username = username;
        this.website = website;
        this.bio = bio;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void updateProfileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

    Boolean isEqualPassword(String password){
        return this.password.equals(password);
    }

}
