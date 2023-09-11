package com.movte.slate.community.application.usecase;

import com.movte.slate.community.application.request.AddCommentServiceRequest;
import com.movte.slate.community.application.response.AddCommentServiceResponse;

public interface AddCommentUseCase {
    AddCommentServiceResponse addComment(Long userId, long feedId, AddCommentServiceRequest serviceRequest);
}
