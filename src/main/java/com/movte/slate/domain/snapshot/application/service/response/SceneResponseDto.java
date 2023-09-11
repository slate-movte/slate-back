package com.movte.slate.domain.snapshot.application.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SceneResponseDto {

    private long sceneId;
    private String imageUrl;
}
