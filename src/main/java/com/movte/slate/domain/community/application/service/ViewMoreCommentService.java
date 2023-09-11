package com.movte.slate.domain.community.application.service;

import com.movte.slate.domain.community.application.response.ViewMoreCommentServiceResponse;
import com.movte.slate.domain.community.application.usecase.ViewMoreCommentUseCase;
import com.movte.slate.domain.community.application.port.ViewCommentPort;
import com.movte.slate.domain.community.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewMoreCommentService implements ViewMoreCommentUseCase {
    private final ViewCommentPort viewCommentPort;

    @Override
    public ViewMoreCommentServiceResponse viewMoreComments(long feedId) {
        List<Comment> comments = viewCommentPort.view(feedId);
        return new ViewMoreCommentServiceResponse(comments);
    }
}
