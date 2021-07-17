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

    public List<Post> findAllByUserId(String userId){
        return postRepository.findAllByUserId(userId);
    }

    public Post findOneByPostId(Long postId){
        return postRepository.findById(postId).get();
    }

    public void save(Post post){
        postRepository.save(post);
        Member member = memberService.findByUserId(post.getMember().getId());
        member.postCountInc();
    }

    public void delete(Long postId){
        Post post = postRepository.findById(postId).get();
        postRepository.delete(post);
        Member member = memberService.findByUserId(post.getMember().getId());
        member.postCountDec();
    }
}
