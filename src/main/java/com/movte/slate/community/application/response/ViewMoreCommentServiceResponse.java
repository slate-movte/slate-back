package com.movte.slate.community.application.response;

import com.movte.slate.community.domain.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class ViewMoreCommentServiceResponse {
    private final List<CommentServiceResponse> comments;

    public ViewMoreCommentServiceResponse(List<Comment> comments) {
        this.comments = comments.stream().map(CommentServiceResponse::new).toList();
    }
}
