package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Feed;

import java.util.Optional;

public interface FindFeedByIdPort {

    Optional<Feed> findById(long feedId);
}
