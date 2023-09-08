package com.movte.slate.domain.stillcut.repository;

import com.movte.slate.domain.stillcut.domain.StillCut;
import com.movte.slate.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StillCutJpaAdapter implements FindStillCutByUserPort {
    private final StillCutRespository stillCutRespository;

    @Override
    public List<StillCut> findByUser(User findUser) {
        return stillCutRespository.findByUser(findUser);
    }
}
