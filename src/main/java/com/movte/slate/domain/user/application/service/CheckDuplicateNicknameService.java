package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.port.FindUserByNicknamePort;
import com.movte.slate.domain.user.application.usecase.CheckDuplicateNicknameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckDuplicateNicknameService implements CheckDuplicateNicknameUseCase {
    private final FindUserByNicknamePort findUserByNicknamePort;

    @Override
    public boolean checkIfNicknameIsDuplicate(String nickname) {
        return findUserByNicknamePort.find(nickname).isPresent();
    }
}
