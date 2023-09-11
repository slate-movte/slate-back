package com.movte.slate.domain.community.application.usecase;

import com.movte.slate.domain.community.application.response.ViewOtherUserFeedsServiceResponse;
import com.movte.slate.domain.community.application.request.ViewOtherUserFeedsServiceRequest;

public interface ViewOtherUserFeedsUseCase {
    ViewOtherUserFeedsServiceResponse view(ViewOtherUserFeedsServiceRequest serviceRequest);
}
