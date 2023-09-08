package com.movte.slate.community.application.usecase;

public interface CancelLikeUseCase {
    void cancel(long userId, long likedFeedId);
}
