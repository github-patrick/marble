package com.example.marble.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UniversityExistsException extends RuntimeException {
    public UniversityExistsException(String message) {
        super(message);
    }
}
