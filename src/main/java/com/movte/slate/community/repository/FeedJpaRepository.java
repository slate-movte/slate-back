package com.movte.slate.community.repository;

import com.movte.slate.community.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedJpaRepository extends JpaRepository<Feed, Long> {
}
