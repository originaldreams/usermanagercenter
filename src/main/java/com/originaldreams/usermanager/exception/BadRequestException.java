package com.originaldreams.usermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BadRequestException extends RuntimeException{

    private ErrorDetails details;

    public ErrorDetails getDetails() {
        return details;
    }

    public void setDetails(ErrorDetails details) {
        this.details = details;
    }

    public BadRequestException(ErrorDetails errorDetails) {
        super();
        this.details = errorDetails;
    }

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}

