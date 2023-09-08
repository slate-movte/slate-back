package com.movte.slate.community.application.port;

import com.movte.slate.community.domain.Feed;

public interface SaveFeedPort {
    Feed save(Feed feed);
}
