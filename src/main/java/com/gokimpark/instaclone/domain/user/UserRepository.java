package com.gokimpark.instaclone.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);

    User findByEmailOrUsername(String email, String username);
}
