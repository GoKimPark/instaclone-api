package com.gokimpark.instaclone.web.story;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StoryDto {

    @NotBlank
    private String username;
    @NotBlank
    private String imageUrl;
}
