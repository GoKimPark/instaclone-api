package com.gokimpark.instaclone.domain.post.dto;

import com.gokimpark.instaclone.domain.post.Post;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

@Data
public class PostDetailDto {

    @ReadOnlyProperty
    private Integer id;
    private String imageUrl;
    private String caption;
    private String location;

    private Long likesCount;

    public PostDetailDto(){}
    public PostDetailDto(Post post, Long likesCount) {
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
        this.caption = post.getCaption();
        this.location = post.getLocation();
        this.likesCount = likesCount;
    }
}
