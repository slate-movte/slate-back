package com.movte.slate.community.repository;

import com.movte.slate.community.application.port.*;
import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FeedJpaAdapter implements FindFeedByIdPort,
        FindFirstFeedPageByUserPort,
        CheckThatOtherUserIsFollowedByUserPort,
        FindFeedPageByUserInRangePort,
        SaveFeedPort {
    private final FeedJpaRepository feedJpaRepository;


    @Override
    public Optional<Feed> find(long feedId) {
        return feedJpaRepository.findById(feedId);
    }

    @Override
    public List<Feed> find(User otherUser, int pageSize) {
        return null;
    }

    @Override
    public boolean check(User user, User otherUser) {
        return false;
    }

    @Override
    public List<Feed> find(User user, long lastFeedId, long pageSize) {
        // lastFeedId 부터 pageSize까지의 데이터를 가져온다.
        return null;
    }

    @Override
    public Feed save(Feed feed) {
        return null;
    }
}
