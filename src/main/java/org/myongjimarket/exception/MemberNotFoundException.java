package org.myongjimarket.exception;

import lombok.Data;
import org.myongjimarket.code.ErrorCode;

@Data
public class MemberNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
}
