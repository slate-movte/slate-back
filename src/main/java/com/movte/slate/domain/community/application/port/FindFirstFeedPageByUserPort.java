package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.domain.Page;

public interface FindFirstFeedPageByUserPort {
    Page<Feed> findFirstFeedPageByUserPort(User otherUser, int pageSize);
}
