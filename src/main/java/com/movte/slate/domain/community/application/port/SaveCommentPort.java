package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.community.domain.Comment;

public interface SaveCommentPort {
    Comment save(Comment comment);
}
