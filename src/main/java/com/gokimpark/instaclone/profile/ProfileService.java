package com.gokimpark.instaclone.profile;

import com.gokimpark.instaclone.member.Member;
import com.gokimpark.instaclone.member.MemberService;
import com.gokimpark.instaclone.post.PostService;
import com.gokimpark.instaclone.story.Story;
import com.gokimpark.instaclone.story.StoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public ProfileDto findProfile(String userId) {
        Member member = memberService.findByUserId(userId);

        ProfileDto profile = new ProfileDto();
        profile.setMemberInfo(ProfileMemberInfoDto.from(member));

        TypeToken<List<ProfilePostDto>> postsTypeToken = new TypeToken<>() {};
        profile.setPosts(mapper.map(postService.findAllByUserId(member.getId()), postsTypeToken.getType()));
        TypeToken<List<ProfileStoryDto>> storiesTypeToken = new TypeToken<>() {};
        profile.setStories(mapper.map(storyService.findAllByUserId(member.getId()), storiesTypeToken.getType()));

        return profile;
    }

    public Story findStoryByStoryId(Long id) {
        return storyService.findOneByStoryId(id);
    }
}

