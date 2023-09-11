package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Comment;

import java.util.List;

public interface ViewCommentPort {
    List<Comment> view(long feedId);
}
