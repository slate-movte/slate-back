package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;

public interface DeleteLikePort {
    void deleteByUserAndFeed(User user, Feed feed);
}
