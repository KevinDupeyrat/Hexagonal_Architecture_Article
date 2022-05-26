package com.architecture.hexagonal.example.domain.movies.service;

import com.architecture.hexagonal.example.domain.movies.error.MoviesNotFoundException;
import com.architecture.hexagonal.example.domain.movies.model.Movie;
import com.architecture.hexagonal.example.domain.movies.outbound.MoviesProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    private final MoviesProvider moviesProvider;

    @Override
    public List<Movie> getPopulars() {
        List<Movie> movies = moviesProvider.getPopulars();
        if (CollectionUtils.isEmpty(movies)) {
            throw new MoviesNotFoundException("Populard movies not found");
        }
        return movies;
    }

    @Override
    public List<Movie> getUpcoming() {
        List<Movie> movies = moviesProvider.getUpcoming();
        if (CollectionUtils.isEmpty(movies)) {
            throw new MoviesNotFoundException("Lastet movies not found");
        }
        return movies;
    }

    @Override
    public Movie getById(String id) {
        Optional<Movie> movie = moviesProvider.getById(id);
        if (movie.isEmpty()) {
            throw new MoviesNotFoundException(String.format("Movie with id [%s] not found", id));
        }
        return movie.get();
    }
}
