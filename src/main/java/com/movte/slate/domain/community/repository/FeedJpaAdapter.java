package com.movte.slate.domain.community.repository;

import com.movte.slate.domain.community.application.port.FindFeedByIdPort;
import com.movte.slate.domain.community.application.port.FindFeedPageByUserInRangePort;
import com.movte.slate.domain.community.application.port.FindFirstFeedPageByUserPort;
import com.movte.slate.domain.community.application.port.SaveFeedPort;
import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FeedJpaAdapter implements FindFeedByIdPort,
        FindFirstFeedPageByUserPort,
        FindFeedPageByUserInRangePort,
        SaveFeedPort {
    private final FeedJpaRepository feedJpaRepository;


    @Override
    public Optional<Feed> findById(long feedId) {
        return feedJpaRepository.findById(feedId);
    }

    @Override
    public Page<Feed> findFirstFeedPageByUserPort(User otherUser, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(0, pageSize, sort);
        return feedJpaRepository.findAll(pageRequest);
    }

    @Override
    public List<Feed> findFeedPageByUserInRange(User writer, long lastFeedId, int pageSize) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Page<Feed> feeds = feedJpaRepository.findByWriterAndIdLessThan(writer, lastFeedId, pageRequest);
        return feeds.stream().toList();
    }

    @Override
    public Feed save(Feed feed) {
        return feedJpaRepository.save(feed);
    }
}
