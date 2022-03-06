package com.gokimpark.instaclone.domain.user.dto;

import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Email;

@Data
public class UserDto {

    @ReadOnlyProperty
    private Long id;

    @Email @Column(nullable = false)
    private String email;
    private String name;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    private String profileImageUrl;
    private String webSite;

    @Lob
    private String bio;

    @Column(unique = true)
    private String phoneNumber;
}
