package com.movte.slate.global.exception;

public abstract class HttpBusinessException extends BusinessException {

    public abstract int getStatusCode();
}
