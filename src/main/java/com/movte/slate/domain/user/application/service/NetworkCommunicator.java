package com.movte.slate.domain.user.application.service;

public interface NetworkCommunicator {
    String getResourceFromKakao(String url, String queryString);

    String getResponseUsingGet(String url, String queryString);
}
