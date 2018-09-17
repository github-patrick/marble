package com.example.marble.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private Date timestamp;
    private String details;
    private String message;
    private int status;
}
