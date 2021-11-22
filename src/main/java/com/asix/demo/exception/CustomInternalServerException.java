package com.asix.demo.exception;

public class CustomInternalServerException extends  RuntimeException {
    public CustomInternalServerException(String message) {
        super(message);
    }

    public CustomInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
