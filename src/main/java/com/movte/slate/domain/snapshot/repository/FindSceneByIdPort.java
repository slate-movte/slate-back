package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.Scene;
import java.util.Optional;

public interface FindSceneByIdPort {
    Optional<Scene> findById(long sceneId);
}
