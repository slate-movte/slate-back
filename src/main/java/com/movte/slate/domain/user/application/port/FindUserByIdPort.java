package com.movte.slate.domain.user.application.port;

import com.movte.slate.domain.user.domain.User;

import java.util.Optional;

public interface FindUserByIdPort {
    Optional<User> findById(long userId);
}
