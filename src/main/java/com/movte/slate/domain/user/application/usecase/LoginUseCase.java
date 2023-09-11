package com.movte.slate.domain.user.application.usecase;

import com.movte.slate.domain.user.application.service.request.LoginServiceRequest;
import com.movte.slate.domain.user.application.service.response.LoginServiceResponse;

public interface LoginUseCase {
    LoginServiceResponse login(LoginServiceRequest serviceRequest);
}
