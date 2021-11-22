package com.asix.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errorDetails = new ArrayList<>();
        for (var error: ex.getAllErrors()) {
            errorDetails.add(error.getDefaultMessage());
        }

        var httpStatusCode = HttpStatus.BAD_REQUEST;

        var failedValidation = new ApiException("Validation Failed", httpStatusCode, errorDetails);

        return new ResponseEntity(failedValidation, httpStatusCode);
    }
}
