package com.example.marble.exception;

public class StudentNotFoundForTeacherException extends RuntimeException {
    public StudentNotFoundForTeacherException(String message) {
        super(message);
    }
}
