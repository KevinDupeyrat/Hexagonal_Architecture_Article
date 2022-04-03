package com.architecture.hexagonal.exemple.movies.error;

import com.architecture.hexagonal.exemple.movies.domain.movies.error.MoviesNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MoviesNotFoundException.class)
    public ResponseEntity<String> moviesNotFoundExceptionHandler(final MoviesNotFoundException moviesNotFoundException) {
        return ResponseEntity
                .internalServerError()
                .body(moviesNotFoundException.getMessage());
    }

    @ExceptionHandler(ParametersException.class)
    public ResponseEntity<String> badRequest(final ParametersException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }
}
