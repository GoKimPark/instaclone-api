package com.gokimpark.instaclone.domain.profile;

import com.gokimpark.instaclone.domain.member.Member;
import com.gokimpark.instaclone.domain.member.MemberService;
import com.gokimpark.instaclone.domain.post.PostService;
import com.gokimpark.instaclone.domain.story.Story;
import com.gokimpark.instaclone.domain.story.StoryService;
import com.gokimpark.instaclone.web.profile.ProfileDto;
import com.gokimpark.instaclone.web.profile.ProfileMemberInfoDto;
import com.gokimpark.instaclone.web.profile.ProfilePostDto;
import com.gokimpark.instaclone.web.profile.ProfileStoryDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private MemberService memberService;
    private PostService postService;
    private StoryService storyService;

    ModelMapper mapper = new ModelMapper();

    public ProfileDto findProfile(String username) {
        Member member = memberService.findByEmail(username);

        ProfileDto profile = new ProfileDto();
        profile.setMemberInfo(ProfileMemberInfoDto.from(member));

        TypeToken<List<ProfilePostDto>> postsTypeToken = new TypeToken<>() {};
        profile.setPosts(mapper.map(postService.findAllByUsername(member.getName()), postsTypeToken.getType()));

        TypeToken<List<ProfileStoryDto>> storiesTypeToken = new TypeToken<>() {};
        profile.setStories(mapper.map(storyService.findAllByUsername(member.getName()), storiesTypeToken.getType()));

        return profile;
    }

    public Story findStoryByStoryId(String id) {
        return storyService.findByStoryId(id);
    }
}

