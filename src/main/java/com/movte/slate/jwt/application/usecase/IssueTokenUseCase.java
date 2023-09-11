package com.movte.slate.jwt.application.usecase;

public interface IssueTokenUseCase {
    String createAccessToken(Long id);

    String createRefreshToken(Long id);
}
