package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Transactional
    @Modifying
    @Query(value = "delete from follow where to_user = :toUser and from_user = :fromUser", nativeQuery = true)
    void unFollow(User toUser, User fromUser);

    Long countByToUser(User user);
    Long countByFromUser(User user);

    @Query(value = "select to_user from follow where from_user = :user", nativeQuery = true)
    List<User> findAllByFromUser(User user);

    @Query(value = "select from_user from follow where to_user = :user", nativeQuery = true)
    List<User> findAllByToUser(User user);

    void deleteAllByFromUser(User user);
    void deleteAllByToUser(User user);

}
