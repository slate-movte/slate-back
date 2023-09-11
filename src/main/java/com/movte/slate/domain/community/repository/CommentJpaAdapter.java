package com.movte.slate.domain.community.repository;

import com.movte.slate.domain.community.application.port.SaveCommentPort;
import com.movte.slate.domain.community.application.port.ViewCommentPort;
import com.movte.slate.domain.community.domain.Comment;
import com.movte.slate.domain.community.domain.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentJpaAdapter implements ViewCommentPort, SaveCommentPort {
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> view(long feedId) {
        Feed feed = Feed.builder().id(feedId).build();
        return commentJpaRepository.findCommentByFeed(feed);
    }

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(comment);
    }
}
