package com.gokimpark.instaclone.web.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostCreateDto {

    @NotBlank
    private String username;
    @NotBlank
    private String imageUrl;
    private String caption;
    private String location;
}
