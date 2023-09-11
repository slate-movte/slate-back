package com.movte.slate.domain.community.repository;

import com.movte.slate.domain.community.domain.Follow;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowee(User user, User otherUser);
}
