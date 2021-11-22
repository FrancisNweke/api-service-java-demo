package com.asix.demo.exception;

public class EmailValidatorException extends RuntimeException {
    public EmailValidatorException(String message) {
        super(message);
    }

    public EmailValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
