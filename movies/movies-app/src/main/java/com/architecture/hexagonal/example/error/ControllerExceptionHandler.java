package com.architecture.hexagonal.example.error;

import com.architecture.hexagonal.example.domain.movies.error.MoviesNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MoviesNotFoundException.class)
    public ResponseEntity<String> moviesNotFoundExceptionHandler(final MoviesNotFoundException moviesNotFoundException) {
        return ResponseEntity
                .noContent()
                .build();
    }

    @ExceptionHandler(ParametersException.class)
    public ResponseEntity<String> badRequest(final ParametersException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }
}
