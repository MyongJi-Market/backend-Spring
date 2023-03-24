package org.myongjimarket.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.myongjimarket.code.ResponseCode;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResResult<T> {
    private ResponseCode responseCode;
    private int code;
    private String message;
    private T data;

    @Builder
    public ResResult(ResponseCode responseCode, int code, String message, T data) {
        this.responseCode = responseCode;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
