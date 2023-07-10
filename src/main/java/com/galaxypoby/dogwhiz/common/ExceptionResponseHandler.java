package com.galaxypoby.dogwhiz.common;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice("com.galaxypoby.dogwhiz")
public class ExceptionResponseHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({CustomException.class})
    public CustomResponse CustomException(CustomException e) {
        log.error(e.getLocalizedMessage());
        return new CustomResponse<>(e.getErrorCode());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public CustomResponse SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        log.error(e.getLocalizedMessage());
        return new CustomResponse<>(e.getErrorCode(), null, e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public CustomResponse MaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getLocalizedMessage());
        return new CustomResponse<>(ErrorCode.FILE_OVER_SIZE);
    }
}
