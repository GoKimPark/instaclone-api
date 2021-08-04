package com.gokimpark.instaclone.domain.story;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter
public class Story {

    @Id
    @Column(unique = true)
    private String id;

    private String url;
    private String username;

    protected Story(){
        id = UUID.randomUUID().toString();
    }
}
