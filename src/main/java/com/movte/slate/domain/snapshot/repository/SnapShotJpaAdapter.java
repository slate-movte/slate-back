package com.movte.slate.domain.snapshot.repository;

import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SnapShotJpaAdapter implements FindSnapShotByUserPort {
    private final SnapShotRepository snapShotRepository;

    @Override
    public List<Snapshot> findByUser(User findUser) {
        return snapShotRepository.findByUser(findUser);
    }
}
