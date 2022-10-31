package com.example.backend.common.exception;

import org.springframework.http.HttpStatus;

public class ErrorResult {

    private int code;
    private String message;
    private HttpStatus httpStatus;

    public ErrorResult(int code,
                       String message,
                       HttpStatus httpStatus) {

        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    static ErrorResult create(int code,
                              String message,
                              HttpStatus httpStatus) {

        return new ErrorResult(code, message, httpStatus);
    }

    static ErrorResult serverError(Exception e) {
        return new ErrorResult(500, "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return String.format("code: {}, message: {}, httpstatus:{}", code, message, httpStatus);
    }
}
