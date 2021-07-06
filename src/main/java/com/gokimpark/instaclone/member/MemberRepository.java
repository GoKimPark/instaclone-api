package com.gokimpark.instaclone.member;

import com.gokimpark.instaclone.account.MemberJoinInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {
    private static final Map<String, Member> store = new HashMap<>(); // ConcurrentHashMap 으로 변경예정
    private static long sequence = 0L; //  AtomicLong 으로 변경예정


    public Member save(MemberJoinInfo memberJoinInfo){
        if(store.containsKey(memberJoinInfo.getEmail())) return null;
        Member member = new Member(memberJoinInfo);
        return member;
    }
    public Member findById(String UserId){ return store.get(UserId);
    }
}
