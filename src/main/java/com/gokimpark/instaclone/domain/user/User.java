package com.gokimpark.instaclone.domain.user;

import com.gokimpark.instaclone.domain.post.Post;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String username; // userId

    @NotBlank
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

    public void updateProfileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

    Boolean isEqualPassword(String password){
        return this.password.equals(password);
    }

}
