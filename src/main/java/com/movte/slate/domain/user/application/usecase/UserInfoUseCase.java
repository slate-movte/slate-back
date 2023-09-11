package com.movte.slate.domain.user.application.usecase;

import com.movte.slate.domain.user.application.service.request.EditUserInfoServiceRequest;
import com.movte.slate.domain.user.application.service.response.GetUserInfoResponse;

public interface UserInfoUseCase {
    GetUserInfoResponse getUserInfo(long userId);

    void editUserInfo(long userId, EditUserInfoServiceRequest editUserInfoServiceRequest);
}
