package com.example.backend.common.exception.notification;

import com.example.backend.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum NotificationTypeException implements BaseExceptionType {

    NOTIFICATION_NOT_FOUND_EXCEPTION(404, "알림을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    NotificationTypeException(int errorCode,
                              String message,
                              HttpStatus httpStatus) {

        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
