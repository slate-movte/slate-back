package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.snapshot.domain.Scene;

public interface SaveScenePort {

    Scene save(Scene scene);
}
