package com.architecture.hexagonal.exemple.movies.error;

public class ParametersException extends RuntimeException {
    public ParametersException(String message) {
        super(message);
    }
}
