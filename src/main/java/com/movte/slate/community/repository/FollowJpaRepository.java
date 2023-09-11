package com.movte.slate.community.repository;

import com.movte.slate.community.domain.Follow;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerAndFollowee(User user, User otherUser);
}
