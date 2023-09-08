package com.movte.slate.community.application.service;

import com.movte.slate.community.application.usecase.LikeFeedUseCase;
import org.springframework.stereotype.Service;

@Service
public class LikeFeedService implements LikeFeedUseCase {
    @Override
    public void recordLike(long userId, long likedFeedId) {

    }
}
