package com.gokimpark.instaclone.post;

import com.gokimpark.instaclone.member.Member;
import com.gokimpark.instaclone.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;

    public PostService(PostRepository postRepository, MemberService memberService){
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    public List<Post> findAllByUsername(String username){
        return postRepository.findAllByUsername(username);
    }

    public Post findByPostId(Long postId){
        return postRepository.getById(postId);
    }

    public void save(Post post){
        postRepository.save(post);
        Member member = memberService.findByUserEmail(post.getMember().getEmail());
        member.postCountInc();
    }

    public void delete(Long postId){
        Post post = postRepository.findById(postId).get();
        postRepository.delete(post);
        Member member = memberService.findByUserEmail(post.getMember().getEmail());
        member.postCountDec();
    }
}
