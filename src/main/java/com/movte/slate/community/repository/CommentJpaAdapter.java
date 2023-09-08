package com.movte.slate.community.repository;

import com.movte.slate.community.application.port.ViewCommentPort;
import com.movte.slate.community.domain.Comment;
import com.movte.slate.community.domain.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentJpaAdapter implements ViewCommentPort {
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> view(long feedId) {
        Feed feed = Feed.builder().id(feedId).build();
        return commentJpaRepository.findCommentByFeed(feed);
    }
}
