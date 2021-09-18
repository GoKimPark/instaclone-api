package com.gokimpark.instaclone.web.post;

import lombok.Getter;
import org.springframework.data.annotation.ReadOnlyProperty;

@Getter
public class PostEditDto {

    @ReadOnlyProperty
    private Integer PostId;
    private String caption;
    private String location;
}
