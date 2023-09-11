package com.movte.slate.community.repository;

import com.movte.slate.community.application.port.DeleteLikePort;
import com.movte.slate.community.application.port.SaveLikePort;
import com.movte.slate.community.domain.Feed;
import com.movte.slate.community.domain.Like;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LikeJpaAdapter implements SaveLikePort, DeleteLikePort {
    private final LikeJpaRepository likeJpaRepository;

    @Override
    public void save(Like like) {
        likeJpaRepository.save(like);
    }


    @Override
    public void deleteByUserAndFeed(User user, Feed feed) {
        likeJpaRepository.deleteByUserAndFeed(user, feed);
    }
}
