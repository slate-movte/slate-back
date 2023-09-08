package com.movte.slate.community.application.usecase;

public interface LikeFeedUseCase {
    void recordLike(long userId, long likedFeedId);
}
