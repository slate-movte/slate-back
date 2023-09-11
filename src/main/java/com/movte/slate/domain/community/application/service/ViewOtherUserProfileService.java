package com.movte.slate.domain.community.application.service;

import com.movte.slate.domain.community.application.response.ViewOtherUserProfileServiceResponse;
import com.movte.slate.domain.community.application.usecase.ViewOtherUserProfileUseCase;
import com.movte.slate.domain.community.application.port.CheckThatOtherUserIsFollowedByUserPort;
import com.movte.slate.domain.community.application.port.FindFirstFeedPageByUserPort;
import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewOtherUserProfileService implements ViewOtherUserProfileUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final FindFirstFeedPageByUserPort findFirstFeedPageByUserPort;
    private final CheckThatOtherUserIsFollowedByUserPort checkThatOtherUserIsFollowedByUserPort;

    @Override
    public ViewOtherUserProfileServiceResponse viewOtherUserProfile(long userId, long otherUserId, int feedCount) {
        User user = findUserByIdPort.findById(userId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        User otherUser = findUserByIdPort.findById(otherUserId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        Page<Feed> feeds = findFirstFeedPageByUserPort.findFirstFeedPageByUserPort(otherUser, feedCount);
        boolean isFollowed = checkThatOtherUserIsFollowedByUserPort.checkThatOtherUserIsFollowedByUser(user, otherUser);
        return new ViewOtherUserProfileServiceResponse(otherUser, feeds, isFollowed);
    }
}