package com.instituto.restapi.Instituto.app.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorDetails {

    private  Date timestamp;
    private  String message;
    private  Integer status;
    private  String details;


    public ErrorDetails(Date timestamp, String message, Integer status, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }
}
