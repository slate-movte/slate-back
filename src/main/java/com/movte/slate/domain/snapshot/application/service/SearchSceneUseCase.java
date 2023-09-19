package com.movte.slate.domain.snapshot.application.service;


import com.movte.slate.domain.snapshot.application.service.response.SceneDetailResponseDto;
import com.movte.slate.domain.snapshot.domain.Scene;
import com.movte.slate.domain.snapshot.repository.SceneRepository;
import com.movte.slate.global.exception.NotFoundException;
import com.movte.slate.global.exception.NotFoundExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchSceneUseCase {

    private final SceneRepository sceneRepository;

    public SceneDetailResponseDto searchDetail(Long scenedId) {
        Scene scene = sceneRepository.findById(scenedId)
            .orElseThrow(() -> new NotFoundException(NotFoundExceptionCode.NOT_FOUND_SCENE));
        return SceneDetailResponseDto.from(scene);
    }
}
