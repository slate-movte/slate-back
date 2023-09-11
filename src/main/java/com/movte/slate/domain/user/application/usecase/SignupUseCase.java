package com.movte.slate.domain.user.application.usecase;

import com.movte.slate.domain.user.application.service.request.SignupServiceRequest;
import com.movte.slate.domain.user.application.service.response.SignupServiceResponse;

public interface SignupUseCase {
    SignupServiceResponse signup(SignupServiceRequest serviceRequest);
}
