package com.gokimpark.instaclone.web.post;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostCreateDto {

    @NotBlank
    private String username;
    @NotBlank
    private String imageUrl;
    private String caption;
    private String location;
}
