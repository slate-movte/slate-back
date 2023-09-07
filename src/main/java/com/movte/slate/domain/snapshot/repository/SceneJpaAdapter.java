package com.movte.slate.domain.snapshot.repository;


import com.movte.slate.domain.movie.domain.Scene;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SceneJpaAdapter implements FindSceneByIdPort{
    private final SceneRepository sceneRepository;

    @Override
    public Optional<Scene> findById(long sceneId) {
        return sceneRepository.findById(sceneId);
    }
}
