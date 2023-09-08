package com.movte.slate.community.application.service;

import com.movte.slate.community.application.port.CheckThatOtherUserIsFollowedByUserPort;
import com.movte.slate.community.application.port.FindFirstFeedPageByUserPort;
import com.movte.slate.community.application.response.ViewOtherUserProfileServiceResponse;
import com.movte.slate.community.application.usecase.ViewOtherUserProfileUseCase;
import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.FindUserByIdPort;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewOtherUserProfileService implements ViewOtherUserProfileUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final FindFirstFeedPageByUserPort findFirstFeedPageByUserPort;
    private final CheckThatOtherUserIsFollowedByUserPort checkThatOtherUserIsFollowedByUserPort;

    @Override
    public ViewOtherUserProfileServiceResponse viewOtherUserProfile(long userId, long otherUserId) {
        User user = findUserByIdPort.findById(userId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        User otherUser = findUserByIdPort.findById(otherUserId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        int pageSize = 30;
        List<Feed> feeds = findFirstFeedPageByUserPort.find(otherUser, pageSize);
        boolean isFollowed = checkThatOtherUserIsFollowedByUserPort.check(user, otherUser);
        return new ViewOtherUserProfileServiceResponse(otherUser, feeds, isFollowed);
    }
}
