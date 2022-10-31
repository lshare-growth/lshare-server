package com.example.backend.common.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final BaseExceptionType baseExceptionType;

    public BusinessException(BaseExceptionType baseExceptionType) {
        super(baseExceptionType.getMessage());
        this.baseExceptionType = baseExceptionType;
    }

    public BaseExceptionType getBaseExceptionType() {
        return baseExceptionType;
    }

    public int getErrorCode() {
        return baseExceptionType.getErrorCode();
    }

    public String getMessage() {
        return baseExceptionType.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return baseExceptionType.getHttpStatus();
    }
}
