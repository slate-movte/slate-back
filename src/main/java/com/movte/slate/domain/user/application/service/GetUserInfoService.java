package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.service.dto.response.UserInfoGetResponse;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.FindUserByIdPort;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserInfoService {
    private final FindUserByIdPort findUserByIdPort;

    /**
     * 유저 정보 (자기 자신의 정보) 가져오기
     *
     * @param userId 유저의 아이디
     * @return 유저 정보
     */
    public UserInfoGetResponse getUserInfo(long userId) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BadRequestException(BadRequestExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        return new UserInfoGetResponse(user.getId(), user.getNickname(), user.getProfileImageUrl());
    }
}
