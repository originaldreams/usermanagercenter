package com.originaldreams.usermanager.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorDetails<T> {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss SSS")
    private Date timestamp;
    private String message;
    private T details;


    public ErrorDetails(String message) {
        this.timestamp = new Date();
    }

    public ErrorDetails(String message, T details) {
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }
}
