package com.gokimpark.instaclone.profile;

import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    public Profile getProfile(String userId) {
        return new Profile();
    }
}
