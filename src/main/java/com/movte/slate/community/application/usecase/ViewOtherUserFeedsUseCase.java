package com.movte.slate.community.application.usecase;

import com.movte.slate.community.application.request.ViewOtherUserFeedsServiceRequest;
import com.movte.slate.community.application.response.ViewOtherUserFeedsServiceResponse;

public interface ViewOtherUserFeedsUseCase {
    ViewOtherUserFeedsServiceResponse view(ViewOtherUserFeedsServiceRequest serviceRequest);
}
