package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.usecase.UserInfoUseCase;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.application.port.SaveProfileImagePort;
import com.movte.slate.domain.user.application.port.SaveUserPort;
import com.movte.slate.domain.user.application.service.request.EditUserInfoServiceRequest;
import com.movte.slate.domain.user.application.service.response.GetUserInfoResponse;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@Service
public class UserInfoService implements UserInfoUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final SaveUserPort saveUserPort;
    private final SaveProfileImagePort saveProfileImagePort;

    @Override
    public GetUserInfoResponse getUserInfo(long userId) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        return new GetUserInfoResponse(user.getId(), user.getNickname(), user.getProfileImageUrl());
    }

    @Override
    public void editUserInfo(long userId, EditUserInfoServiceRequest request) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        requireNonNull(request.getFile());
        String url = saveProfileImagePort.save(request.getFile(), userId);
        user.setProfileImageUrl(url);
        user.setNickname(request.getNickname());
        saveUserPort.save(user);
    }
}
