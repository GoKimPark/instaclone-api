package com.gokimpark.instaclone.domain.post.dto;

import com.gokimpark.instaclone.domain.post.Post;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

@Data
public class PostProfileDto {

    @ReadOnlyProperty
    private Integer id;
    private String imageUrl;

    public PostProfileDto(Post post) {
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
    }
}
