package com.example.capstonehotels.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> GenericException(Exception globalException) {
        globalException.printStackTrace();
        return ResponseEntity.badRequest().body(globalException.getMessage());
    }
}
