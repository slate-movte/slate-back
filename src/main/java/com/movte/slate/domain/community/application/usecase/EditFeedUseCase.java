package com.movte.slate.domain.community.application.usecase;

import com.movte.slate.domain.community.application.request.EditFeedServiceRequest;
import com.movte.slate.domain.community.application.response.EditFeedServiceResponse;

public interface EditFeedUseCase {
    EditFeedServiceResponse edit(Long userId, EditFeedServiceRequest serviceRequest);
}
