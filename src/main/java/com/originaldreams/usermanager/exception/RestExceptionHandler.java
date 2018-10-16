package com.originaldreams.usermanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    private static final String NOT_FOUND_MESSAGE = "你访问的资源不存在";
    private static final String BAD_REQUEST_MESSAGE = "参数错误";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.warn("resource not found, details = {}", ex.getDetails());
        return new ResponseEntity<>(
                ex.getDetails(),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({BadRequestException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
        logger.warn("BadRequestException, details = {}", ex.getDetails());
        return new ResponseEntity<>(ex.getDetails(), HttpStatus.BAD_REQUEST);
    }

}
