package com.movte.slate.domain.community.application.usecase;

public interface CancelLikeUseCase {
    void cancel(long userId, long likedFeedId);
}
