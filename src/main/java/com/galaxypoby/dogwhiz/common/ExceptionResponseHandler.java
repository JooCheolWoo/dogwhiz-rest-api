package com.galaxypoby.dogwhiz.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.galaxypoby.dogwhiz")
public class ExceptionResponseHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({CustomException.class})
    public CustomResponse CustomException(CustomException e) {
        log.error(e.getLocalizedMessage());
        return new CustomResponse<>(e.getErrorCode());
    }
}
