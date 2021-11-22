package com.asix.demo.exception;

import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
    private final List<String> Details;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = ZonedDateTime.now(ZoneId.of("Z"));
        Details = new ArrayList<>();
    }

    public ApiException(String message, HttpStatus httpStatus, List<String> details) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = ZonedDateTime.now(ZoneId.of("Z"));
        Details = details;
    }

    public List<String> getDetails() {
        return Details;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
