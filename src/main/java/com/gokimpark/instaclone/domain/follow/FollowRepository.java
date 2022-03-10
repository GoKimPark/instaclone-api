package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.follow.dto.FollowSimpleListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {

    Optional<Follow> findByToUserAndFromUser(Long toUserId, Long fromUserId);

    Long countByToUser(Long userId);
    Long countByFromUser(Long userId);

    @Query(value = "select new com.gokimpark.instaclone.domain.follow.dto.FollowSimpleListDto(u.username, u.name) from Follow f INNER JOIN User u ON f.toUser = u.id where f.fromUser = :userId")
    List<FollowSimpleListDto> findAllByFromUser(@Param("userId") Long userId);

    @Query(value = "select new com.gokimpark.instaclone.domain.follow.dto.FollowSimpleListDto(u.username, u.name) from Follow f JOIN User u ON f.fromUser = u.id where f.toUser = :userId")
    List<FollowSimpleListDto> findAllByToUser(@Param("userId") Long userId);

    void deleteAllByFromUser(Long userId);
    void deleteAllByToUser(Long userId);

}
