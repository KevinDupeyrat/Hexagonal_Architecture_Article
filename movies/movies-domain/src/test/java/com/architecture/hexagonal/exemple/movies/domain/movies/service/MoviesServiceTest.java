package com.architecture.hexagonal.exemple.movies.domain.movies.service;

import com.architecture.hexagonal.exemple.movies.domain.movies.error.MoviesNotFoundException;
import com.architecture.hexagonal.exemple.movies.domain.movies.model.Movie;
import com.architecture.hexagonal.exemple.movies.domain.model.MovieFactory;
import com.architecture.hexagonal.exemple.movies.domain.movies.outbound.MoviesProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {

    @Mock
    private MoviesProvider moviesProvider;

    private MoviesService moviesService;

    @BeforeEach
    public void init() {
        moviesService = new MoviesServiceImpl(moviesProvider);
    }

    @Test
    void should_fetch_populars() {
        // given
        final List<Movie> movies = List.of(MovieFactory.MOVIE_POJO);
        when(moviesProvider.getPopulars()).thenReturn(movies);
        // when
        final List<Movie> result = moviesService.getPopulars();
        // then
        verify(moviesProvider, times(1)).getPopulars();
        assertEquals(movies, result);

    }

    @Test
    void should_not_fetch_popular() {
        // given
        final List<Movie> movies = List.of();
        when(moviesProvider.getPopulars()).thenReturn(movies);
        // when - then
        assertThrows(MoviesNotFoundException.class, () -> moviesService.getPopulars());
        // then
        verify(moviesProvider, times(1)).getPopulars();
    }

    @Test
    void should_fetch_lastest() {
        // given
        final List<Movie> movies = List.of(MovieFactory.MOVIE_POJO);
        when(moviesProvider.getUpcoming()).thenReturn(movies);
        // when
        final List<Movie> result = moviesService.getUpcoming();
        // then
        verify(moviesProvider, times(1)).getUpcoming();
        assertEquals(movies, result);
    }

    @Test
    void should_not_fetch_lastest() {
        // given
        final List<Movie> movies = List.of();
        when(moviesProvider.getUpcoming()).thenReturn(movies);
        // when - then
        assertThrows(MoviesNotFoundException.class, () -> moviesService.getUpcoming());
        // then
        verify(moviesProvider, times(1)).getUpcoming();
    }

    @Test
    void should_get_by_id() {
        // given
        final String id = "Id";
        final Movie movie = MovieFactory.MOVIE_POJO;
        when(moviesProvider.getById(id)).thenReturn(Optional.of(movie));
        // when
        final Movie result = moviesService.getById(id);
        // then
        verify(moviesProvider, times(1)).getById(id);
        assertEquals(movie, result);
    }

    @Test
    void should_not_get_by_id() {
        // given
        final String id = "Id";
        when(moviesProvider.getById(id)).thenReturn(Optional.empty());
        // when
        assertEquals(
                "Movie with id [Id] not found",
                assertThrows(MoviesNotFoundException.class, () -> moviesService.getById(id))
                        .getMessage()
        );
        // then
        verify(moviesProvider, times(1)).getById(id);
    }
}
