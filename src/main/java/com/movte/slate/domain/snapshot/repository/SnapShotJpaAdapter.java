package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.community.application.port.FindSnapshotByIdPort;
import com.movte.slate.domain.snapshot.application.port.FindSnapShotByUserPort;
import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SnapShotJpaAdapter implements FindSnapShotByUserPort, FindSnapshotByIdPort {
    private final SnapShotJpaRepository snapShotJpaRepository;

    @Override
    public List<Snapshot> findByUser(User findUser) {
        return snapShotJpaRepository.findByUser(findUser);
    }

    @Override
    public Optional<Snapshot> find(Long id) {
        return snapShotJpaRepository.findById(id);
    }
}
