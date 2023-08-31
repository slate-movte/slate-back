package com.movte.slate.domain.user.repository;

import com.movte.slate.domain.user.domain.OAuthProvider;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthIdAndOauthProvider(String oauthId, OAuthProvider oAuthProvider);

    Boolean existsByNickname(String nickname);
}
