package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnapShotJpaRepository extends JpaRepository<Snapshot, Long> {

    List<Snapshot> findByUser(User findUser);
}
