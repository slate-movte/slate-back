package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.service.dto.request.EditUserInfoServiceRequest;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.FindUserByIdPort;
import com.movte.slate.domain.user.repository.SaveUserPort;
import com.movte.slate.file.SaveProfileImagePort;
import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class EditUserInfoService {
    private final FindUserByIdPort findUserByIdPort;
    private final SaveUserPort saveUserPort;
    private final SaveProfileImagePort saveProfileImagePort;

    /**
     * 유저 정보를 수정
     *
     * @param userId 유저의 아이디
     */
    public void editUserInfo(long userId, EditUserInfoServiceRequest request) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        requireNonNull(request.getFile());
        Optional<String> urlOpt = saveProfileImagePort.save(request.getFile(), userId);
        if (urlOpt.isEmpty()) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
        String url = urlOpt.get();
        user.setProfileImageUrl(url);
        user.setNickname(request.getNickname());
        saveUserPort.save(user);
    }

}
