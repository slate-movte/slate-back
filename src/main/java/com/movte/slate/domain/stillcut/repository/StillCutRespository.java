package com.movte.slate.domain.stillcut.repository;

import com.movte.slate.domain.stillcut.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StillCutRespository extends JpaRepository<Snapshot, Long> {

    List<Snapshot> findByUser(User findUser);
}
