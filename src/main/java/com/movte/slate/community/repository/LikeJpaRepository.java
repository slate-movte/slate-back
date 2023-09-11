package com.movte.slate.community.repository;

import com.movte.slate.community.domain.Feed;
import com.movte.slate.community.domain.Like;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {
    void deleteByUserAndFeed(User user, Feed feed);
}
