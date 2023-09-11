package com.movte.slate.domain.user.application.usecase;

import com.movte.slate.domain.user.application.service.response.LoginServiceResponse;

public interface LoginForDevelopUseCase {
    LoginServiceResponse login(String oauthId);
}
