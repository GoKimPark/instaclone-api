package com.gokimpark.instaclone.domain.profile;

import com.gokimpark.instaclone.domain.post.Post;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.web.user.dto.ProfilePostDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final PostService postService;

    ModelMapper mapper = new ModelMapper();

    public List<ProfilePostDto> getProfilePosts(User user){
        List<Post> posts = postService.findAllByUser(user);
        TypeToken<List<ProfilePostDto>> postsTypeToken = new TypeToken<>() {};
        return mapper.map(posts, postsTypeToken.getType());
    }
}

