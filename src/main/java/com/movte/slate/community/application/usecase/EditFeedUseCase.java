package com.movte.slate.community.application.usecase;

import com.movte.slate.community.application.request.EditFeedServiceRequest;
import com.movte.slate.community.application.response.EditFeedServiceResponse;

public interface EditFeedUseCase {
    EditFeedServiceResponse edit(Long userId, EditFeedServiceRequest serviceRequest);
}
