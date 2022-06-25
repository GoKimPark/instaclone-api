package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {

    Optional<Follow> findByToUserAndFromUser(Long toUserId, Long fromUserId);

    Long countByToUser(Long userId);
    Long countByFromUser(Long userId);

    @Query(value = "select u from Follow f INNER JOIN User u ON f.toUser = u.id where f.fromUser = :userId")
    List<User> findAllByFromUser(@Param("userId") Long userId);

    @Query(value = "select u from Follow f INNER JOIN User u ON f.fromUser = u.id where f.toUser = :userId")
    List<User> findAllByToUser(@Param("userId") Long userId);

    void deleteAllByFromUser(Long userId);
    void deleteAllByToUser(Long userId);

}
