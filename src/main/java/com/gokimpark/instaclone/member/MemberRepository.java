package com.gokimpark.instaclone.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("SELECT m FROM Member m WHERE m.id = :id")
    @Transactional(readOnly = true)
    Member findByUserId(String id);
}
