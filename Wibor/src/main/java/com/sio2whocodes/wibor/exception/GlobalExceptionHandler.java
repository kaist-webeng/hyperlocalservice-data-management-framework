package com.sio2whocodes.wibor.exception;

import com.sio2whocodes.wibor.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ResponseData> handleResourceNotFoundException(ResourceNotFoundException e) {
        logger.info("{} ({}) :: {}", e.getResponseCode(), e.getResponseCode().getHttpStatus().toString(), e.getResponseCode().getDetail());
        return ResponseData.toResponseEntity(e.getResponseCode());
    }
}
