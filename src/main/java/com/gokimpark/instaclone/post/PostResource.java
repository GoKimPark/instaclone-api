package com.gokimpark.instaclone.post;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;

//@Embeddable
public class PostResource {
    private List<String> resourceUrl;

    private String caption;
    private List<String> tagUserId;
    private String location;

    public PostResource(){}
    public PostResource(List<String> resourceUrl, String caption, List<String> tagUserId, String location) {
        this.resourceUrl = resourceUrl;
        this.caption = caption;
        this.tagUserId = tagUserId;
        this.location = location;
    }
}
