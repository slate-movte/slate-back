package com.movte.slate.community.application.request;

import lombok.Getter;

@Getter
public class EditFeedServiceRequest {
    private Long feedId;
    private String feedTitle;
    private String feedContent;
    private Long snapshotId;
}
