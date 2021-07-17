package com.gokimpark.instaclone.profile;

import com.gokimpark.instaclone.member.Member;
import com.gokimpark.instaclone.member.MemberService;
import com.gokimpark.instaclone.post.PostService;
import com.gokimpark.instaclone.story.StoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {
    private final MemberService memberService;
    private final PostService postService;
    private final StoryService storyService;

    public ProfileService(MemberService memberService, PostService postService, StoryService storyService) {
        this.memberService = memberService;
        this.postService = postService;
        this.storyService = storyService;
    }

    ModelMapper mapper = new ModelMapper();

    public ProfileDto getProfile(String userId) {
        Member member = memberService.findByUserId(userId);

        ProfileDto profile = new ProfileDto();
        profile.setMemberInfo(mapper.map(member, ProfileMemberInfoDto.class));
        profile.setPosts(mapper.map(postService.findAllByUserId(member.getId()), ProfilePostDto.class));
        profile.setStories(mapper.map(storyService.findAllByUserId(member.getId()), ProfileStoryDto.class));

        return profile;
    }
}

