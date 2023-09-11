package com.movte.slate.domain.community.application.service;

import com.movte.slate.domain.community.application.response.UploadFeedServiceResponse;
import com.movte.slate.domain.community.application.usecase.UploadFeedUseCase;
import com.movte.slate.domain.community.application.port.FindSnapshotByIdPort;
import com.movte.slate.domain.community.application.port.SaveFeedPort;
import com.movte.slate.domain.community.application.request.UploadFeedServiceRequest;
import com.movte.slate.domain.community.domain.Feed;
import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UploadFeedService implements UploadFeedUseCase {
    private final SaveFeedPort saveFeedPort;
    private final FindSnapshotByIdPort findSnapshotByIdPort;

    @Override
    @Transactional
    public UploadFeedServiceResponse upload(long userId, UploadFeedServiceRequest serviceRequest) {
        Feed feed = serviceRequest.toFeed();
        feed = saveFeedPort.save(feed);
        long snapshotId = serviceRequest.getSnapshotId();
        Snapshot snapshot = findSnapshotByIdPort.find(snapshotId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NO_RESOURCE));
        feed.setSnapshot(snapshot);
        return new UploadFeedServiceResponse(feed.getId());
    }
}
