package com.movte.slate.domain.community.application.service;

import com.movte.slate.domain.community.application.usecase.LikeFeedUseCase;
import com.movte.slate.domain.community.application.port.FindFeedByIdPort;
import com.movte.slate.domain.community.application.port.SaveLikePort;
import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.domain.community.domain.Like;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeFeedService implements LikeFeedUseCase {
    private final FindFeedByIdPort findFeedByIdPort;
    private final FindUserByIdPort findUserByIdPort;
    private final SaveLikePort saveLikePort;

    @Override
    @Transactional
    public void recordLike(long userId, long likedFeedId) {
        Feed feed = findFeedByIdPort.findById(likedFeedId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NO_RESOURCE));
        User user = findUserByIdPort.findById(userId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        Like like = Like.builder().feed(feed).user(user).build();
        saveLikePort.save(like);
    }
}