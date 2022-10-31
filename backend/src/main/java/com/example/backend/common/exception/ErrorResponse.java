package com.example.backend.common.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private final LocalDateTime time;
    private int code;
    private final String message;
    private final List<Throwable> fieldErrors;
    private final HttpStatus httpStatus;

    public ErrorResponse(Exception exception) {
        this.time = LocalDateTime.now();
        this.code = ErrorCode.SERVER_ERROR.getErrorCode();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.fieldErrors = new ArrayList<>();
        this.message = exception.getMessage();
    }

    public ErrorResponse(Exception exception, ErrorCode errorCode) {
        this.time = LocalDateTime.now();
        this.code = errorCode.getErrorCode();
        this.httpStatus = errorCode.getStatus();
        this.fieldErrors = List.of(exception.getCause());
        this.message = exception.getMessage();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Throwable> getFieldErrors() {
        return fieldErrors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return String.format("Time: %s, Code: %s, Message: %s, HttpStatus: %s, Value: %s", time, code, message, httpStatus, fieldErrors);
    }
}
