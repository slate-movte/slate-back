package com.movte.slate.community.repository;

import com.movte.slate.community.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {
}
