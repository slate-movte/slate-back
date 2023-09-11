package com.movte.slate.domain.user.application.usecase;

import com.movte.slate.domain.user.application.service.request.LoginServiceRequest;
import com.movte.slate.domain.user.application.service.request.SignupServiceRequest;
import com.movte.slate.domain.user.application.service.response.LoginServiceResponse;
import com.movte.slate.domain.user.application.service.response.SignupServiceResponse;

public interface UserAuthUseCase {
    LoginServiceResponse login(LoginServiceRequest serviceRequest);

    SignupServiceResponse signup(SignupServiceRequest serviceRequest);


}
