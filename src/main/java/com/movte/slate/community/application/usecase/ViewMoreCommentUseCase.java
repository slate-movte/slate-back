package com.movte.slate.community.application.usecase;

import com.movte.slate.community.application.response.ViewMoreCommentServiceResponse;

public interface ViewMoreCommentUseCase {
    ViewMoreCommentServiceResponse viewMoreComments(long commentsFeedId);
}
