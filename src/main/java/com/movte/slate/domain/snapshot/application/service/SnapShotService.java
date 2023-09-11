package com.movte.slate.domain.snapshot.application.service;

import com.movte.slate.domain.snapshot.application.service.request.InsertSnapShotServiceRequest;
import com.movte.slate.domain.snapshot.application.service.response.InsertSnapShotServiceResponse;
import com.movte.slate.domain.snapshot.domain.Scene;
import com.movte.slate.domain.snapshot.domain.Snapshot;
import com.movte.slate.domain.snapshot.repository.FindSceneByIdPort;
import com.movte.slate.domain.snapshot.repository.SnapShotRepository;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.FindUserByIdPort;
import com.movte.slate.file.SaveSnapShotFilePort;
import com.movte.slate.global.exception.*;
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
    private final SnapShotRepository snapShotRepository;

    public InsertSnapShotServiceResponse insertSnapshot(long userId, InsertSnapShotServiceRequest request) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        requireNonNull(request.getFile());
        Optional<String> urlOpt = saveSnapShotFilePort.saveSnapShot(request.getFile(), userId);
        if (urlOpt.isEmpty()) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
        String url = urlOpt.get();
        Optional<Scene> scene = findSceneByIdPort.findById(request.getSceneId());
        if (scene.isEmpty()) {
            throw new BadRequestException(BadRequestExceptionCode.NO_RESOURCE);
        }
        Snapshot snapshot = Snapshot.builder()
                .user(user)
                .imageUrl(url)
                .scene(scene.get())
                .build();
        snapShotRepository.save(snapshot);
        return new InsertSnapShotServiceResponse(url);
    }
}
