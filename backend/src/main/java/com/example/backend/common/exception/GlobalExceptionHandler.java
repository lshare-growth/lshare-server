package com.example.backend.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalStateException(IllegalStateException exception) {
        ErrorResponse response = new ErrorResponse(exception, ErrorCode.BAD_REQUEST);
        log.error(response.toString());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception) {
        ErrorResponse response = new ErrorResponse(exception, ErrorCode.BAD_REQUEST);
        log.error(response.toString());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> numberFormatException(NumberFormatException exception) {
        ErrorResponse response = new ErrorResponse(exception, ErrorCode.BAD_REQUEST);
        log.error(response.toString());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResult> businessException(BusinessException e) {
        ErrorResult response = ErrorResult.create(e.getErrorCode(), e.getMessage(), e.getHttpStatus());
        log.error(response.toString());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalError.class)
    public ResponseEntity<ErrorResult> unresolvedException(Exception exception) {
        ErrorResult response = ErrorResult.serverError(exception);
        log.error(response.toString());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
