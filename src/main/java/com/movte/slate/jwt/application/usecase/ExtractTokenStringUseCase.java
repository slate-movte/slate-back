package com.movte.slate.jwt.application.usecase;

public interface ExtractTokenStringUseCase {
    String extractTokenString(String authorizationHeader);
}