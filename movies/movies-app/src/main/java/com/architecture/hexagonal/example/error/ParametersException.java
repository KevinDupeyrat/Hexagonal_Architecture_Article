package com.architecture.hexagonal.example.error;

public class ParametersException extends RuntimeException {
    public ParametersException(String message) {
        super(message);
    }
}
