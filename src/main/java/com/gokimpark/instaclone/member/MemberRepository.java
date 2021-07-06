package com.gokimpark.instaclone.member;

import com.gokimpark.instaclone.account.MemberJoinInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends Repository<Member, String> {
    @Query("SELECT member FROM Member member WHERE member.UserId =:id")
    @Transactional(readOnly = true)
    Member findById(@Param("id") String id);

    Member save(MemberJoinInfo memberJoinInfo);
}
