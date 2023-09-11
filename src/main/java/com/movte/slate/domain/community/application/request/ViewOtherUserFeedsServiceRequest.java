package com.movte.slate.domain.community.application.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ViewOtherUserFeedsServiceRequest {
    private final long userId;
    private final long lastFeedId;
    private final int pageSize;
}
