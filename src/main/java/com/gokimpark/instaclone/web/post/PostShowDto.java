package com.gokimpark.instaclone.web.post;

import lombok.Builder;
import lombok.Data;

@Data
public class PostShowDto {

    private Integer postId;
    private String username;
    private String imageUrl;

    private String likesCount;

    @Builder
    public PostShowDto (Integer postId, String username, String imageUrl, String likesCount){
        this.postId = postId;
        this.username = username;
        this.imageUrl = imageUrl;
        this.likesCount = likesCount;
    }
}
