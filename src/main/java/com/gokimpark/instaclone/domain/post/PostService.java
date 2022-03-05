package com.gokimpark.instaclone.domain.post;

import com.gokimpark.instaclone.domain.comment.CommentRepository;
import com.gokimpark.instaclone.domain.exception.PostException;
import com.gokimpark.instaclone.domain.exception.UserException;
import com.gokimpark.instaclone.domain.like.Likes;
import com.gokimpark.instaclone.domain.like.LikesRepository;
import com.gokimpark.instaclone.domain.post.dto.PostDetailDto;
import com.gokimpark.instaclone.domain.post.dto.PostProfileDto;
import com.gokimpark.instaclone.domain.user.User;
import com.gokimpark.instaclone.domain.user.UserRepository;
import com.gokimpark.instaclone.web.post.dto.PostCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostDetailDto create(PostCreateDto postCreateDto){
        User user = userRepository.findByUsername(postCreateDto.getUsername()).orElseThrow(UserException::new);

        Post post = Post.builder()
                .imageUrl(postCreateDto.getImageUrl())
                .caption(postCreateDto.getCaption())
                .location(postCreateDto.getLocation())
                .user(user)
                .build();

        Post savedPost = postRepository.save(post);
        return new PostDetailDto(savedPost, 0L);
    }

    @Transactional
    public PostDetailDto update(Integer postId, String caption, String location) {
        Post post = postRepository.findById(postId).orElseThrow(PostException::new);

        post.update(caption, location);
        return findByPostId(postId);
    }

    public List<PostProfileDto> findAllProfilePostByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        List<Post> posts = postRepository.findAllByUser(user);

        return posts.stream()
                .map(PostProfileDto::new)
                .collect(Collectors.toList());
    }

    public Long findCountPost(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserException::new);
        return postRepository.countAllByUser(user);
    }

    public PostDetailDto findByPostId(Integer postId){
        Post post = postRepository.findById(postId).orElseThrow(PostException::new);
        List<Likes> likes = likesRepository.findAllByPost(post);
        return new PostDetailDto(post, (long) likes.size());
    }

    public void delete(Integer postId){
        Post post = postRepository.findById(postId).orElseThrow(PostException::new);
        postRepository.delete(post);
        likesRepository.deleteAllByPost(post);
        commentRepository.deleteAllByPost(post);
    }

    public void deleteAllByUser(User user) {
        postRepository.deleteAllByUser(user);
    }
}
