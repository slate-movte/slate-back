package com.movte.slate.jwt.application.usecase;

import com.movte.slate.domain.user.application.service.request.RefreshAccessTokenServiceRequest;
import com.movte.slate.domain.user.application.service.response.RefreshAccessTokenServiceResponse;

public interface ReissueUseCase {
    RefreshAccessTokenServiceResponse reissue(RefreshAccessTokenServiceRequest serviceRequest);
}
