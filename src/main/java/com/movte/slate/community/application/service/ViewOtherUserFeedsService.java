package com.movte.slate.community.application.service;

import com.movte.slate.community.application.port.FindFeedPageByUserInRangePort;
import com.movte.slate.community.application.request.ViewOtherUserFeedsServiceRequest;
import com.movte.slate.community.application.response.ViewOtherUserFeedsServiceResponse;
import com.movte.slate.community.application.usecase.ViewOtherUserFeedsUseCase;
import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.FindUserByIdPort;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewOtherUserFeedsService implements ViewOtherUserFeedsUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final FindFeedPageByUserInRangePort findFeedPageByUserInRangePort;

    @Override
    public ViewOtherUserFeedsServiceResponse view(ViewOtherUserFeedsServiceRequest serviceRequest) {
        long userId = serviceRequest.getUserId();
        User user = findUserByIdPort.findById(userId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        long lastFeedId = serviceRequest.getLastFeedId();
        int pageSize = serviceRequest.getPageSize();
        List<Feed> feeds = findFeedPageByUserInRangePort.findFeedPageByUserInRange(user, lastFeedId, pageSize);
        return new ViewOtherUserFeedsServiceResponse(feeds);
    }
}
