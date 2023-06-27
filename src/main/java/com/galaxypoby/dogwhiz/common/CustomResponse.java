package com.galaxypoby.dogwhiz.common;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomResponse<T> {
    private int status = 0;
    private T data;
    private String message;

    public CustomResponse(ErrorCode errorCode, T data) {
        this.status = errorCode.getStatus();
        this.data = data;
        this.message = errorCode.getMessage();
    }

    public CustomResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.data = (T) errorCode;
        this.message = errorCode.getMessage();
    }
}
