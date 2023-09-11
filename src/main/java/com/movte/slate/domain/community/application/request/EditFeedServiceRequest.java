package com.movte.slate.domain.community.application.request;

import lombok.Getter;

@Getter
public class EditFeedServiceRequest {
    private Long feedId;
    private String feedTitle;
    private String feedContent;
    private Long snapshotId;
}
