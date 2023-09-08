package com.movte.slate.community.application.port;

import com.movte.slate.community.domain.Like;

public interface SaveLikePort {
    void save(Like like);
}
