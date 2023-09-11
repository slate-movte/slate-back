package com.movte.slate.domain.user.application.port;

import com.movte.slate.domain.user.domain.OauthProvider;
import com.movte.slate.domain.user.domain.User;

import java.util.Optional;

public interface FindUserByOauthIdAndOauthProviderPort {
    Optional<User> findByOauthIdAndOauthProvider(String oauthId, OauthProvider provider);
}
