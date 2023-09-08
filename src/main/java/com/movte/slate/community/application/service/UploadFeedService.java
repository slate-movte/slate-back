package com.movte.slate.community.application.service;

import com.movte.slate.community.application.request.UploadFeedServiceRequest;
import com.movte.slate.community.application.response.UploadFeedServiceResponse;
import com.movte.slate.community.application.usecase.UploadFeedUseCase;
import org.springframework.stereotype.Service;

@Service
public class UploadFeedService implements UploadFeedUseCase {
    @Override
    public UploadFeedServiceResponse upload(long userId, UploadFeedServiceRequest serviceRequest) {
        return null;
    }
}
