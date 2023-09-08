package com.movte.slate.domain.stillcut.repository;

import com.movte.slate.domain.stillcut.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;
import java.util.List;

public interface FindStillCutByUserPort {

    List<Snapshot> findByUser(User findUser);
}
