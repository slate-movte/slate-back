package com.movte.slate.domain.user.repository;

import com.movte.slate.domain.user.domain.User;

public interface SaveUserPort {
    User save(User user);
}
