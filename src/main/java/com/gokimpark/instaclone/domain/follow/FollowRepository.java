package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {

    Optional<Follow> findByToUserAndFromUser(Long toUserId, Long fromUserId);

    Long countByToUser(Long userId);
    Long countByFromUser(Long userId);

    List<User> findAllByFromUser(Long userId);
    List<User> findAllByToUser(Long userId);

    void deleteAllByFromUser(Long userId);
    void deleteAllByToUser(Long userId);

}
