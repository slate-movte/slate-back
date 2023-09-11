package com.movte.slate.domain.community.application.response;

import com.movte.slate.domain.community.domain.Feed;
import lombok.Getter;

import java.util.List;

@Getter
public class ViewOtherUserFeedsServiceResponse {
    private final List<FeedServiceResponse> feeds;

    public ViewOtherUserFeedsServiceResponse(List<Feed> feeds) {
        this.feeds = feeds.stream().map(FeedServiceResponse::new).toList();
    }
}
