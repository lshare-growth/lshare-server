package com.example.backend.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 400
    BAD_REQUEST(400, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, "권한이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    NOT_FOUND(404, "해당 자원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 500
    SERVER_ERROR(500, "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int errorCode;
    private final String message;
    private final HttpStatus status;

    ErrorCode(int errorCode,
              String message,
              HttpStatus status) {

        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
