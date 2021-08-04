package com.gokimpark.instaclone.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByUsername(String username);
    Member findByPhoneNumber(String phoneNumber);

    //Member findByEmailOrUsernameOrPhoneNumber(String email, String username, String phoneNumber);
}
