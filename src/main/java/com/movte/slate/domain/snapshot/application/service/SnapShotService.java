package com.movte.slate.domain.snapshot.application.service;

import com.movte.slate.domain.community.application.port.SaveSnapShotFilePort;
import com.movte.slate.domain.snapshot.application.service.request.InsertSnapShotServiceRequest;
import com.movte.slate.domain.snapshot.application.service.response.InsertSnapShotServiceResponse;
import com.movte.slate.domain.snapshot.domain.Scene;
import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.snapshot.application.port.FindSceneByIdPort;
import com.movte.slate.domain.snapshot.repository.SnapShotJpaRepository;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class SnapShotService {
    private final FindUserByIdPort findUserByIdPort;
    private final SaveSnapShotFilePort saveSnapShotFilePort;
    private final FindSceneByIdPort findSceneByIdPort;
    private final SnapShotJpaRepository snapShotJpaRepository;

    public InsertSnapShotServiceResponse insertSnapshot(long userId, InsertSnapShotServiceRequest request) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        requireNonNull(request.getFile());
        String url = saveSnapShotFilePort.saveSnapShot(request.getFile(), userId);

        Optional<Scene> scene = findSceneByIdPort.findById(request.getSceneId());
        if (scene.isEmpty()) {
            throw new BadRequestException(BadRequestExceptionCode.NO_RESOURCE);
        }
        Snapshot snapshot = Snapshot.builder()
                .user(user)
                .imageUrl(url)
                .scene(scene.get())
                .build();
        snapShotJpaRepository.save(snapshot);
        return new InsertSnapShotServiceResponse(url);
    }
}
