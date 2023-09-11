package com.movte.slate.domain.community.application.usecase;

import com.movte.slate.domain.community.application.response.UploadFeedServiceResponse;
import com.movte.slate.domain.community.application.request.UploadFeedServiceRequest;

public interface UploadFeedUseCase {

    UploadFeedServiceResponse upload(long userId, UploadFeedServiceRequest serviceRequest);
}
