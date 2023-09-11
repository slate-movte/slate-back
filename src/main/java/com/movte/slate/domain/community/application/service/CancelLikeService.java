package com.movte.slate.domain.community.application.service;

import com.movte.slate.domain.community.application.port.DeleteLikePort;
import com.movte.slate.domain.community.application.usecase.CancelLikeUseCase;
import com.movte.slate.domain.community.application.port.FindFeedByIdPort;
import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelLikeService implements CancelLikeUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final FindFeedByIdPort findFeedByIdPort;
    private final DeleteLikePort deleteLikePort;

    @Override
    public void cancel(long userId, long likedFeedId) {
        User user = findUserByIdPort.findById(userId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        Feed feed = findFeedByIdPort.findById(likedFeedId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NO_RESOURCE));
        deleteLikePort.deleteByUserAndFeed(user, feed);
    }
}
