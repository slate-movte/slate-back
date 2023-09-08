package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.stillcut.domain.StillCut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapShotRepository extends JpaRepository<StillCut, Long> {

}
