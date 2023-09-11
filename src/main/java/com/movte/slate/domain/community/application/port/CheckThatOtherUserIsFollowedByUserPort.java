package com.movte.slate.domain.community.application.port;

import com.movte.slate.domain.user.domain.User;

public interface CheckThatOtherUserIsFollowedByUserPort {
    boolean checkThatOtherUserIsFollowedByUser(User user, User otherUser);
}
