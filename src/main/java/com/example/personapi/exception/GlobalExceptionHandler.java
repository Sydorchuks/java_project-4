package com.example.personapi.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public Map<String, String> handleApiException(ApiException e) {
        return Map.of(
                "error", e.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> handleAny(Exception e) {
        return Map.of(
                "error", "Something went wrong"
        );
    }
}
