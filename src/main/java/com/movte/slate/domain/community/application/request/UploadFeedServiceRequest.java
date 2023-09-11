package com.movte.slate.domain.community.application.request;

import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import lombok.Getter;


@Getter
public class UploadFeedServiceRequest {
    private final String title;
    private final String content;
    private final long snapshotId;

    public UploadFeedServiceRequest(String title, String content, long snapshotId) {
        if (title.length() <= 3) throw new BadRequestException(BadRequestExceptionCode.INVALID_INPUT);
        this.title = title;
        if (content.length() <= 5) throw new BadRequestException(BadRequestExceptionCode.INVALID_INPUT);
        this.content = content;
        this.snapshotId = snapshotId;
    }

    public Feed toFeed() {
        return Feed.builder()
                .title(title)
                .content(content)
                .build();
    }
}
