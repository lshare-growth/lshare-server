package com.example.backend.common.exception.study;

import com.example.backend.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum StudyTypeException implements BaseExceptionType {

    STUDY_NOT_FOUND_EXCEPTION(404, "해당 스터디를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    StudyTypeException(int errorCode,
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
