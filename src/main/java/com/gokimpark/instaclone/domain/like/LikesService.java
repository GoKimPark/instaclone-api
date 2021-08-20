package com.gokimpark.instaclone.domain.like;

import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    public void addLike(Likes likes){
        likesRepository.save(likes);
    }

    public void unLike(Post post, User user){
        likesRepository.deleteByPostAndUser(post, user);
    }

    public Long countByPost(Post post) { return likesRepository.countAllByPost(post); }
}
