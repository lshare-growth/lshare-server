package com.example.backend.common.exception.study;

import com.example.backend.common.exception.BaseExceptionType;
import com.example.backend.common.exception.BusinessException;

public class StudyTerminatedException extends BusinessException {

    private static final String ERROR_MESSAGE = "해당 스터디는 이미 종료되었습니다.";

    public StudyTerminatedException(BaseExceptionType baseExceptionType) {
        super(baseExceptionType);
    }
}
