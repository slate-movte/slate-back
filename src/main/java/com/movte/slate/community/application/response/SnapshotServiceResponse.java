package com.movte.slate.community.application.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SnapshotServiceResponse {
    private final String url;
    private final String detail;
}
