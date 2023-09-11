package com.movte.slate.domain.movie.dto;


import com.movte.slate.domain.snapshot.domain.Scene;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SceneImageResponseDto {

    private Long sceneId;
    private String imageUrl;


    @Builder
    public SceneImageResponseDto(Long sceneId, String imageUrl) {
        this.sceneId = sceneId;
        this.imageUrl = imageUrl;
    }

    public static SceneImageResponseDto from(Scene scene) {
        return SceneImageResponseDto.builder()
            .sceneId(scene.getSceneId())
            .imageUrl(scene.getImageUrl())
            .build();
    }
}
