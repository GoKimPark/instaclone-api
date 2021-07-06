package com.gokimpark.instaclone.profile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDto {

    public static ProfileDto from(Profile profile) {
        return new ProfileDto().builder()
                .build();
    }
}
