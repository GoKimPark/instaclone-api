package com.gokimpark.instaclone.domain.post;

import com.gokimpark.instaclone.domain.member.Member;
import com.gokimpark.instaclone.domain.member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;
    private MemberService memberService;

    public List<Post> findAllByUsername(String username){
        return postRepository.findAllByUsername(username);
    }

    public Post findByPostId(String postId){
        return postRepository.getById(postId);
    }

    public void save(Post post, String username){
        post.setUsername(username);
        postRepository.save(post);

        Member member = memberService.findByUsername(username);
        member.postCountInc();
    }

    public void delete(String postId){
        Post post = postRepository.findById(postId).get();
        postRepository.delete(post);

        Member member = memberService.findByUsername(post.getUsername());
        member.postCountDec();
    }
}
