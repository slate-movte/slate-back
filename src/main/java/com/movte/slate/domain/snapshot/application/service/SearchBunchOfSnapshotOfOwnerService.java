package com.movte.slate.domain.snapshot.application.service;

import com.movte.slate.domain.snapshot.application.service.dto.SnapshotResponseDto;
import com.movte.slate.domain.snapshot.application.service.response.SearchBunchOfSnapshotOfOwnerServiceResponse;
import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.snapshot.application.port.FindSnapShotByUserPort;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchBunchOfSnapshotOfOwnerService {
    private final FindUserByIdPort findUserByIdPort;
    private final FindSnapShotByUserPort findSnapShotByUserPort;

    public SearchBunchOfSnapshotOfOwnerServiceResponse searchBunchOfSnapshotOfOwner(long userId, long findUserId) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        Optional<User> idUserOpt = findUserByIdPort.findById(findUserId);
        if (idUserOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User findUser = idUserOpt.get();
        List<Snapshot> userSnapshots = findSnapShotByUserPort.findByUser(findUser);
        List<SnapshotResponseDto> snapshotResponseDtos = new ArrayList<>();
        for (Snapshot snapshot : userSnapshots) {
            snapshotResponseDtos.add(new SnapshotResponseDto(snapshot.getSnapshotId(),
                    snapshot.getImageUrl()));
        }
        return SearchBunchOfSnapshotOfOwnerServiceResponse.builder().scenes(snapshotResponseDtos).build();
    }
}
