package com.architecture.hexagonal.example.infra.api.service;

import com.architecture.hexagonal.example.domain.movies.model.Movie;
import com.architecture.hexagonal.example.domain.movies.outbound.MoviesProvider;
import com.architecture.hexagonal.example.infra.api.mapper.MovieMapper;
import info.movito.themoviedbapi.TmdbMovies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheMoviesDBService implements MoviesProvider {

    private final TmdbMovies tmdbMovies;

    @Override
    public List<Movie> getPopulars() {
        return tmdbMovies.getPopularMovies("en", 1)
                .getResults()
                .stream()
                .map(MovieMapper::toDomain)
                .toList();
    }

    @Override
    public List<Movie> getUpcoming() {
        return tmdbMovies.getUpcoming("en", 1, "FR")
                .getResults()
                .stream()
                .map(MovieMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Movie> getById(String id) {
        return Optional.ofNullable(MovieMapper
                .toDomain(tmdbMovies.getMovie(Integer.parseInt(id), "en")));
    }
}
