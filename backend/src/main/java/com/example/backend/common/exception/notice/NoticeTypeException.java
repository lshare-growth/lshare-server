package com.example.backend.common.exception.notice;

import com.example.backend.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum NoticeTypeException implements BaseExceptionType {

    NOTICE_STATUS_UNCHANGEABLE_EXCEPTION(400, "해당 공지사항의 상태를 변경할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOTICE_NOT_FOUND_EXCEPTION(404, "해당 공지사항을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    NoticeTypeException(int errorCode,
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
