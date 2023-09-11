package com.movte.slate.domain.snapshot.application.port;

import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;

import java.util.List;

public interface FindSnapShotByUserPort {

    List<Snapshot> findByUser(User findUser);
}
