package com.movte.slate.community.application.service;

import com.movte.slate.community.application.response.ViewMoreCommentServiceResponse;
import com.movte.slate.community.application.usecase.ViewMoreCommentUseCase;
import org.springframework.stereotype.Service;

@Service
public class ViewMoreCommentService implements ViewMoreCommentUseCase {
    @Override
    public ViewMoreCommentServiceResponse viewMoreComments(long userId, long commentsFeedId) {
        return null;
    }
}
