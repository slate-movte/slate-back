package com.movte.slate.domain.community.api.request;

import com.movte.slate.domain.community.application.request.UploadFeedServiceRequest;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class UploadFeedApiRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Positive
    private Long snapshotId;

    public UploadFeedServiceRequest toServiceRequest() {
        return new UploadFeedServiceRequest(title, content, snapshotId);
    }
}
