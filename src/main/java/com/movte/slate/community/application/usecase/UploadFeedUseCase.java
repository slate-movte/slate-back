package com.movte.slate.community.application.usecase;

import com.movte.slate.community.application.request.UploadFeedServiceRequest;
import com.movte.slate.community.application.response.UploadFeedServiceResponse;

public interface UploadFeedUseCase {

    UploadFeedServiceResponse upload(long userId, UploadFeedServiceRequest serviceRequest);
}
