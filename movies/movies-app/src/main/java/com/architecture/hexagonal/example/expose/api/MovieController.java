package com.architecture.hexagonal.example.expose.api;

import com.architecture.hexagonal.example.domain.movies.model.Movie;
import com.architecture.hexagonal.example.domain.movies.service.MoviesService;
import com.architecture.hexagonal.example.error.ParametersException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MoviesService moviesService;

    @GetMapping("/populars")
    public ResponseEntity<List<Movie>> getPopulars() {
        final List<Movie> movies = moviesService.getPopulars();
        return ResponseEntity
                .ok(movies);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Movie>> getUpcoming() {
        final List<Movie> movies = moviesService.getUpcoming();
        return ResponseEntity
                .ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable("id") final String id) {
        if (!StringUtils.hasText(id)) {
            throw new ParametersException("[id] parameter is mandatory");
        }
        final Movie movie = moviesService.getById(id);
        return ResponseEntity
                .ok(movie);
    }
}
