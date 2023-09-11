package com.movte.slate.domain.community.application.response;

import com.movte.slate.domain.community.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddCommentServiceResponse {

    private final long commentId;
    private final LocalDateTime createdAt;
    private final long feedId;
    private final String commentContent;

    public AddCommentServiceResponse(Comment comment) {
        this.commentId = comment.getId();
        this.createdAt = comment.getCreated_at();
        this.feedId = comment.getFeed().getId();
        this.commentContent = comment.getContent();
    }
}
