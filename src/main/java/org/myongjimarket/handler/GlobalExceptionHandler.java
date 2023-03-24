package org.myongjimarket.handler;

import lombok.extern.slf4j.Slf4j;
import org.myongjimarket.exception.EmailAlreadyExistException;
import org.myongjimarket.exception.MemberNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(final MemberNotFoundException e) {
        log.error("GlobalExceptionHandler: {}", e.getErrorCode());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistException(final EmailAlreadyExistException e) {
        log.error("EmailAlreadyExistException: {}", e.getErrorCode());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }
}
