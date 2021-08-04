package com.gokimpark.instaclone.domain.post;

import com.gokimpark.instaclone.domain.image.Image;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Post {
    @Id
    @Column(unique = true)
    private String id;

    private String caption;
    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;

    private String username;

    protected Post(){
        id = UUID.randomUUID().toString();
    }
}
