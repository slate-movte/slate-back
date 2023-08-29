package com.movte.slate.global.exception;

public abstract class BusinessException extends RuntimeException {
    public abstract String getCode();

}
