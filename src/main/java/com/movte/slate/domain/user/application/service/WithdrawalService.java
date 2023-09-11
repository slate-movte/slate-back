package com.movte.slate.domain.user.application.service;

import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.domain.user.application.port.FindUserByIdPort;
import com.movte.slate.domain.user.application.port.SaveUserPort;
import com.movte.slate.domain.user.application.usecase.WithdrawalUseCase;
import com.movte.slate.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawalService implements WithdrawalUseCase {
    private final FindUserByIdPort findUserByIdPort;
    private final SaveUserPort saveUserPort;

    @Override
    public void withdrawal(Long userId) {
        User user = findUserByIdPort.findById(userId).orElseThrow(() -> new BadRequestException(BadRequestExceptionCode.NOT_USER));
        user.withdrawal();
        saveUserPort.save(user);
    }
}
