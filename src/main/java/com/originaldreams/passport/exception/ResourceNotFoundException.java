package com.originaldreams.passport.exception;

public class ResourceNotFoundException extends RuntimeException{

    private ErrorDetails details;

    public ErrorDetails getDetails() {
        return details;
    }

    public void setDetails(ErrorDetails details) {
        this.details = details;
    }

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(ErrorDetails errorDetails) {
        super();
        this.details = errorDetails;
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
