package com.movte.slate.domain.user.application.port;

import com.movte.slate.domain.user.domain.User;

public interface SaveUserPort {
    User save(User user);
}
