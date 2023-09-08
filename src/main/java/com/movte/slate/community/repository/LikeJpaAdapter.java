package com.movte.slate.community.repository;

import com.movte.slate.community.application.port.SaveLikePort;
import com.movte.slate.community.domain.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LikeJpaAdapter implements SaveLikePort {
    private final LikeJpaRepository likeJpaRepository;

    @Override
    public void save(Like like) {
        likeJpaRepository.save(like);
    }
}
