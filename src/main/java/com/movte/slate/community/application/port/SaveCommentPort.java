package com.movte.slate.community.application.port;

import com.movte.slate.community.domain.Comment;

public interface SaveCommentPort {
    Comment save(Comment comment);
}
