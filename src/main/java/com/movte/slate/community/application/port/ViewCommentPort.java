package com.movte.slate.community.application.port;

import com.movte.slate.community.domain.Comment;

import java.util.List;

public interface ViewCommentPort {
    List<Comment> view(long feedId);
}
