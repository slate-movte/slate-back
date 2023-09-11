package com.movte.slate.community.application.response;

import com.movte.slate.community.domain.Feed;
import lombok.Getter;

@Getter
public class EditFeedServiceResponse {
    private final Long feedId;
    private final String title;
    private final String content;
    private final Long snapshotId;

    public EditFeedServiceResponse(Feed feed) {
        this.feedId = feed.getId();
        this.title = feed.getTitle();
        this.content = feed.getContent();
        this.snapshotId = feed.getSnapshot().getSnapshotId();
    }
}
