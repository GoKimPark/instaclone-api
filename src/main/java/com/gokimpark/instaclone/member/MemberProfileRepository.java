package com.gokimpark.instaclone.member;

import com.gokimpark.instaclone.follow.Following;
import com.gokimpark.instaclone.post.PostRepository;
import com.gokimpark.instaclone.story.StoryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfileRepository {
    private Following followingRepository;
    private PostRepository postRepository;
    private StoryRepository storyRepository;
}
