package com.movte.slate.community.application.service;

import com.movte.slate.community.application.usecase.CancelLikeUseCase;
import org.springframework.stereotype.Service;

@Service
public class CancelLikeService implements CancelLikeUseCase {
    @Override
    public void cancel(long userId, long likedFeedId) {

    }
}
