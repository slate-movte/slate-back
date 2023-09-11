package com.movte.slate.domain.community.application.usecase;

import com.movte.slate.domain.community.application.request.AddCommentServiceRequest;
import com.movte.slate.domain.community.application.response.AddCommentServiceResponse;

public interface AddCommentUseCase {
    AddCommentServiceResponse addComment(Long userId, long feedId, AddCommentServiceRequest serviceRequest);
}
