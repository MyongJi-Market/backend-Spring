package org.myongjimarket.exception;

import lombok.Data;
import org.myongjimarket.code.ErrorCode;

@Data
public class LoginFailureException extends RuntimeException {
    private final ErrorCode errorCode;
}
