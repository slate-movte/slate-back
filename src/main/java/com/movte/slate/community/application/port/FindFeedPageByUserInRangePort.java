package com.movte.slate.community.application.port;

import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;

import java.util.List;

public interface FindFeedPageByUserInRangePort {
    List<Feed> findFeedPageByUserInRange(User user, long lastFeedId, int pageSize);
}
