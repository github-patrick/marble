package com.example.marble.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UniversityNotFoundException.class)
    public ResponseEntity<Object> handleUniversityNotFoundException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false),404);

        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniversityExistsException.class)
    public ResponseEntity<Object> handleUniversityExistsException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false), 400);

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherNotFoundForUniversityException.class)
    public ResponseEntity<Object> handleTeacherNotFoundForUniversityException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false), 404);

        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), ex.getBindingResult().getFieldError().getDefaultMessage(), status.value());

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
