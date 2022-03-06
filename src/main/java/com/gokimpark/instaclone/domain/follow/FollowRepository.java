package com.gokimpark.instaclone.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {

    Optional<Follow> findByToUserAndFromUser(Long toUserId, Long fromUserId);

    Long countByToUser(Long userId);
    Long countByFromUser(Long userId);

    List<Follow> findAllByFromUser(Long userId);
    List<Follow> findAllByToUser(Long userId);

    void deleteAllByFromUser(Long userId);
    void deleteAllByToUser(Long userId);

}
