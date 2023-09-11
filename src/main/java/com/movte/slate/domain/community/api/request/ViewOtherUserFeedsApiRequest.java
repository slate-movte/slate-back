package com.movte.slate.domain.community.api.request;

import com.movte.slate.domain.community.application.request.ViewOtherUserFeedsServiceRequest;
import lombok.Getter;

@Getter
public class ViewOtherUserFeedsApiRequest {
    private long otherUserId;
    private long lastFeedId;
    private int pageSize;

    public ViewOtherUserFeedsServiceRequest toServiceRequest() {
        return new ViewOtherUserFeedsServiceRequest(otherUserId, lastFeedId, pageSize);
    }

}
