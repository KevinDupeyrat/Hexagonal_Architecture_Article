package com.architecture.hexagonal.example.domain.movies.error;

public class MoviesNotFoundException extends RuntimeException {

    public MoviesNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
