package com.movte.slate.domain.user.repository;

import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthIdAndOauthProvider(String oauthId, OauthProvider oauthProvider);

    Optional<User> findByNickname(String nickname);
}
