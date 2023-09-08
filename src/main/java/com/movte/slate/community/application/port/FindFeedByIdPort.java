package com.movte.slate.community.application.port;

import com.movte.slate.community.domain.Feed;

import java.util.Optional;

public interface FindFeedByIdPort {

    Optional<Feed> find(long likedFeedId);
}
