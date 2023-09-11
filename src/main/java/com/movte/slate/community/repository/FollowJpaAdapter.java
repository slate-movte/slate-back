package com.movte.slate.community.repository;

import com.movte.slate.community.application.port.CheckThatOtherUserIsFollowedByUserPort;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowJpaAdapter implements CheckThatOtherUserIsFollowedByUserPort {
    private final FollowJpaRepository followJpaRepository;

    @Override
    public boolean checkThatOtherUserIsFollowedByUser(User user, User otherUser) {
        return followJpaRepository.existsByFollowerAndFollowee(user, otherUser);
    }
}
