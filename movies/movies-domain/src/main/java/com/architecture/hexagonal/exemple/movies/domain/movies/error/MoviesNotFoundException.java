package com.architecture.hexagonal.exemple.movies.domain.movies.error;

public class MoviesNotFoundException extends RuntimeException {
    public MoviesNotFoundException(final String errorMessage) {
        super(errorMessage);
    }

    public MoviesNotFoundException() {
        super();
    }

}
