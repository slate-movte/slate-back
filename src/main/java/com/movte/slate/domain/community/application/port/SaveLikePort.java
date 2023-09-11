package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Like;

public interface SaveLikePort {
    void save(Like like);
}
