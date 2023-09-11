package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;

import java.util.List;

public interface FindFeedsByUserPort {
    List<Feed> find(User otherUser);
}
