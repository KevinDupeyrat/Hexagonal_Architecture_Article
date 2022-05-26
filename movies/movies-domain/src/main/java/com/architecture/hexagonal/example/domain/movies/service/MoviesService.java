package com.architecture.hexagonal.example.domain.movies.service;

import com.architecture.hexagonal.example.domain.movies.model.Movie;

import java.util.List;

public interface MoviesService {
    List<Movie> getPopulars();

    List<Movie> getUpcoming();

    Movie getById(final String id);
}
