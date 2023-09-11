package com.movte.slate.domain.community.application.usecase;

public interface LikeFeedUseCase {
    void recordLike(long userId, long likedFeedId);
}
