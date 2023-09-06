package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.movie.domain.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SceneRepository extends JpaRepository<Scene, Long> {

}
