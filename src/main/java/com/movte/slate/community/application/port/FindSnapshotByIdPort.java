package com.movte.slate.community.application.port;

import com.movte.slate.domain.snapshot.domain.Snapshot;

import java.util.Optional;

public interface FindSnapshotByIdPort {

    Optional<Snapshot> find(Long id);
}
