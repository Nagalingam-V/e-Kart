package com.example.eKart.exception;

import com.example.eKart.products.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage(), "The Product Does not Exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
