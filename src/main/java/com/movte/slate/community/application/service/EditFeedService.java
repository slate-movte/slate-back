package com.movte.slate.community.application.service;

import com.movte.slate.community.application.port.FindFeedByIdPort;
import com.movte.slate.community.application.port.FindSnapshotByIdPort;
import com.movte.slate.community.application.request.EditFeedServiceRequest;
import com.movte.slate.community.application.response.EditFeedServiceResponse;
import com.movte.slate.community.application.usecase.EditFeedUseCase;
import com.movte.slate.community.domain.Feed;
import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditFeedService implements EditFeedUseCase {

    private final FindFeedByIdPort findFeedByIdPort;
    private final FindSnapshotByIdPort findSnapshotByIdPort;

    @Transactional
    @Override
    public EditFeedServiceResponse edit(Long userId, EditFeedServiceRequest serviceRequest) {
        Feed feed = findFeedByIdPort.findById(serviceRequest.getFeedId()).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NO_RESOURCE));
        Snapshot snapshot = findSnapshotByIdPort.find(serviceRequest.getSnapshotId()).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NO_RESOURCE));
        feed.setTitle(serviceRequest.getFeedTitle());
        feed.setContent(serviceRequest.getFeedContent());
        feed.setSnapshot(snapshot);
        return new EditFeedServiceResponse(feed);
    }
}
