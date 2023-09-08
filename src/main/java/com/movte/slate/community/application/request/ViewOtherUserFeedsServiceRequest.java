package com.movte.slate.community.application.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ViewOtherUserFeedsServiceRequest {
    private final long userId;
    private final long lastFeedId;
    private final long pageSize;
}
