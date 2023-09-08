package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StillCutJpaAdapter implements FindStillCutByUserPort {
    private final StillCutRespository stillCutRespository;

    @Override
    public List<Snapshot> findByUser(User findUser) {
        return stillCutRespository.findByUser(findUser);
    }
}
