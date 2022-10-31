package com.example.backend.common.exception.member;

import com.example.backend.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberTypeException implements BaseExceptionType {

    UNAUTHORIZED_EXCEPTION(401, "권한이 존재하지 않습니다..", HttpStatus.UNAUTHORIZED),
    MEMBER_NOT_FOUND_EXCEPTION(404, "해당 회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    STUDY_LEADER_NOT_FOUND_EXCEPTION(404, "스터디 리더를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    MemberTypeException(int errorCode, String message, HttpStatus httpStatus) {
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
