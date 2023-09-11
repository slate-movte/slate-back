package com.movte.slate.domain.community.application.usecase;

import com.movte.slate.domain.community.application.response.ViewMoreCommentServiceResponse;

public interface ViewMoreCommentUseCase {
    ViewMoreCommentServiceResponse viewMoreComments(long commentsFeedId);
}
