package com.movte.slate.community.repository;

import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {
    Page<Feed> findByWriterAndIdLessThan(User writer, long lastFeedId, PageRequest pageRequest);
}
