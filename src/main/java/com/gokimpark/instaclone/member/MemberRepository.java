package com.gokimpark.instaclone.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByUsername(String username);

    Member findByEmailOrUsernameOrPhoneNumber(String email, String username, String phoneNumber);
}
