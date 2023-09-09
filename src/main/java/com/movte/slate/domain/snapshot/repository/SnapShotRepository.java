package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapShotRepository extends JpaRepository<Snapshot, Long> {

    List<Snapshot> findByUser(User findUser);
}
