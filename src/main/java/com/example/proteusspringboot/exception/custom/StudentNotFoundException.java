package com.example.proteusspringboot.exception.custom;



public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
