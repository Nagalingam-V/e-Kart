package com.example.eKart.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime localDateTime;
    private String message;
    private String details;
}
