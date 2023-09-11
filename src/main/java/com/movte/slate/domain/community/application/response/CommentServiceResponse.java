package com.movte.slate.domain.community.application.response;

import com.movte.slate.domain.community.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentServiceResponse {
    private final long commentId;
    private final String contents;
    private final long writerId;
    private final String writerNickname;
    private final LocalDateTime createdAt;

    public CommentServiceResponse(Comment comment) {
        this.commentId = comment.getId();
        this.contents = comment.getContent();
        this.writerId = comment.getWriter().getId();
        this.writerNickname = comment.getWriter().getNickname();
        this.createdAt = comment.getCreated_at();
    }


}
