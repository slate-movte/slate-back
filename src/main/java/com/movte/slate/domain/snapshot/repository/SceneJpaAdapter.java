package com.movte.slate.domain.snapshot.repository;


import com.movte.slate.domain.movie.domain.Movie;
import com.movte.slate.domain.snapshot.domain.Scene;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SceneJpaAdapter implements FindSceneByIdPort, FindSceneByMoviePort{
    private final SceneRepository sceneRepository;

    @Override
    public Optional<Scene> findById(long sceneId) {
        return sceneRepository.findById(sceneId);
    }

    @Override
    public List<Scene> findByMovie(Movie movie) {
        return sceneRepository.findByMovie(movie);
    }
}
