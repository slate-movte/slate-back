package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Feed;

public interface SaveFeedPort {
    Feed save(Feed feed);
}
