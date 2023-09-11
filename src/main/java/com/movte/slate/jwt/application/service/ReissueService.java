package com.movte.slate.jwt.application.service;

import com.movte.slate.jwt.application.usecase.ReissueUseCase;
import com.movte.slate.domain.user.application.service.request.RefreshAccessTokenServiceRequest;
import com.movte.slate.domain.user.application.service.response.RefreshAccessTokenServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ReissueService implements ReissueUseCase {
    @Override
    public RefreshAccessTokenServiceResponse reissue(RefreshAccessTokenServiceRequest serviceRequest) {
        return null;
    }
}
