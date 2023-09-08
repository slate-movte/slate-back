package com.movte.slate.community.application.response;

import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.user.domain.User;
import lombok.Getter;

import java.util.List;

@Getter
public class ViewOtherUserProfileServiceResponse {
    private final long userId;
    private final String nickname;
    private final long countOfFollowed;
    private final long countOfFollowing;
    private final boolean isFollowed;
    private final List<FeedServiceResponse> feeds;

    public ViewOtherUserProfileServiceResponse(User otherUser, List<Feed> feeds, boolean isFollowed) {
        this.userId = otherUser.getId();
        this.nickname = otherUser.getNickname();
        this.countOfFollowed = otherUser.getFollowedList().size();
        this.countOfFollowing = otherUser.getFollowList().size();
        this.isFollowed = isFollowed;
        this.feeds = feeds.stream().map(FeedServiceResponse::new).toList();
    }
}
