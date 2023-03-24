package org.myongjimarket.exception;

import lombok.Data;
import org.myongjimarket.code.ErrorCode;

@Data
public class EmailAlreadyExistException extends RuntimeException {
    private final ErrorCode errorCode;
}
