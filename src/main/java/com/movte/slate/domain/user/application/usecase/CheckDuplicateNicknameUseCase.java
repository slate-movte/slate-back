package com.movte.slate.domain.user.application.usecase;

public interface CheckDuplicateNicknameUseCase {
    boolean checkIfNicknameIsDuplicate(String nickname);
}
