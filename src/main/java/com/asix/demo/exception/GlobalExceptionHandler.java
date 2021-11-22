package com.asix.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex){
        var notFoundStatus = HttpStatus.NOT_FOUND;

        var resourceNotFound = new ApiException(ex.getMessage(),
                notFoundStatus);

        return new ResponseEntity<>(resourceNotFound, notFoundStatus);
    }

    @ExceptionHandler(value = {CustomBadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(CustomBadRequestException ex){
        var badRequest = HttpStatus.BAD_REQUEST;

        var resourceBadRequest = new ApiException(ex.getMessage(),
                badRequest);

        return new ResponseEntity<>(resourceBadRequest, badRequest);
    }

    @ExceptionHandler(value = {CustomInternalServerException.class})
    public ResponseEntity<Object> handleInternalServerError(CustomInternalServerException ex){
        var serverError = HttpStatus.INTERNAL_SERVER_ERROR;

        var internalServer = new ApiException(ex.getMessage(), serverError);

        return new ResponseEntity<>(internalServer, serverError);
    }

    @ExceptionHandler(value = {EmailValidatorException.class})
    public ResponseEntity<Object> handleEmailValidatorError(EmailValidatorException ex){
        var serverError = HttpStatus.CONFLICT;

        var emailValidator = new ApiException(ex.getMessage(), serverError);

        return new ResponseEntity<>(emailValidator, serverError);
    }

}
