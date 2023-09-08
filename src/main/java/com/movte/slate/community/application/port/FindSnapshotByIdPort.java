package com.movte.slate.community.application.port;

import com.movte.slate.domain.stillcut.domain.StillCut;

import java.util.Optional;

public interface FindSnapshotByIdPort {

    Optional<StillCut> find(Long id);
}
