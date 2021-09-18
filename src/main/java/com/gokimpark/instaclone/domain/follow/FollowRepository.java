package com.gokimpark.instaclone.domain.follow;

import com.gokimpark.instaclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {

    Optional<Follow> findByToUserAndFromUser(Integer toUserId, Integer fromUserId);

    Long countByToUser(Integer userId);
    Long countByFromUser(Integer userId);

    List<User> findAllByFromUser(Integer userId);
    List<User> findAllByToUser(Integer userId);

    void deleteAllByFromUser(Integer userId);
    void deleteAllByToUser(Integer userId);

}
