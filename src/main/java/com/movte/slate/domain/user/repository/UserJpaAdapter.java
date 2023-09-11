package com.movte.slate.domain.user.repository;

import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.application.port.FindUserByNicknamePort;
import com.movte.slate.domain.user.application.port.FindUserByOauthIdAndOauthProviderPort;
import com.movte.slate.domain.user.application.port.SaveUserPort;
import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserJpaAdapter implements FindUserByIdPort, SaveUserPort,
        FindUserByOauthIdAndOauthProviderPort, FindUserByNicknamePort {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(long userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findByOauthIdAndOauthProvider(String oauthId, OauthProvider provider) {
        return userJpaRepository.findByOauthIdAndOauthProvider(oauthId, provider);
    }

    @Override
    public Optional<User> find(String nickname) {
        return userJpaRepository.findByNickname(nickname);
    }
}
