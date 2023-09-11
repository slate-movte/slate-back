package com.movte.slate.domain.community.repository;

import com.movte.slate.domain.community.domain.Comment;
import com.movte.slate.domain.community.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByFeed(Feed feed);
}