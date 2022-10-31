package com.example.backend.common.exception.member;

public class DuplicatedStudyMemberException extends RuntimeException {

    private static final String ERROR_MESSAGE = "이미 해당회원이 스터디에 존재합니다.";

    public DuplicatedStudyMemberException() {
        super(ERROR_MESSAGE);
    }

    public DuplicatedStudyMemberException(String message) {
        super(message);
    }
}
