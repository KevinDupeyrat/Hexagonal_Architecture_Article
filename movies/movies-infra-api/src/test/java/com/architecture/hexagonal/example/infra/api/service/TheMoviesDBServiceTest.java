package com.architecture.hexagonal.example.infra.api.service;

import com.architecture.hexagonal.example.domain.movies.model.Movie;
import com.architecture.hexagonal.example.common.utils.DateTimeUtil;
import com.architecture.hexagonal.example.domain.movies.outbound.MoviesProvider;
import com.architecture.hexagonal.example.infra.api.model.MovieDBFactory;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TheMoviesDBServiceTest {

    @Mock
    private TmdbMovies tmdbMovies;

    private MoviesProvider moviesProvider;

    @BeforeEach
    public void init() {
        moviesProvider = new TheMoviesDBService(tmdbMovies);
    }

    @Test
    void should_get_popular() {
        // given
        final MovieDb movieDb = MovieDBFactory.MOVIEDB_POJO;
        final MovieResultsPage movieResultsPage = new MovieResultsPage();
        movieResultsPage.setResults(List.of(movieDb));
        when(tmdbMovies.getPopularMovies("en", 1))
                .thenReturn(movieResultsPage);
        // when
        final List<Movie> result = moviesProvider.getPopulars();
        // then
        assertEquals(1, result.size());
        final Movie movie = result.get(0);
        assertMovieDBequalsMovie(movieDb, movie);
    }

    @Test
    void should_get_upcoming() {
        // given
        final MovieDb movieDb = MovieDBFactory.MOVIEDB_POJO;
        final MovieResultsPage movieResultsPage = new MovieResultsPage();
        movieResultsPage.setResults(List.of(movieDb));
        when(tmdbMovies.getUpcoming("en", 1, "FR"))
                .thenReturn(movieResultsPage);
        // when
        final List<Movie> result = moviesProvider.getUpcoming();
        // then
        assertEquals(1, result.size());
        final Movie movie = result.get(0);
        assertMovieDBequalsMovie(movieDb, movie);
    }

    @Test
    void should_get_by_id() {
        // given
        final MovieDb movieDb = MovieDBFactory.MOVIEDB_POJO;
        when(tmdbMovies.getMovie(movieDb.getId(),"en"))
                .thenReturn(movieDb);
        // when
        final Optional<Movie> result = moviesProvider.getById(String.valueOf(movieDb.getId()));
        // then
        assertTrue(result.isPresent());
        final Movie movie = result.get();
        assertMovieDBequalsMovie(movieDb, movie);
    }

    private void assertMovieDBequalsMovie(final MovieDb movieDb,
                                          final Movie movie) {
        assertEquals(String.valueOf(movieDb.getId()), movie.getId());
        assertEquals(movieDb.getTitle(), movie.getTitle());
        assertEquals(movieDb.getOverview(), movie.getSynopsys());
        assertEquals(movieDb.getPopularity(), movie.getRate().getValue());
        assertEquals(DateTimeUtil.getFromISO(movieDb.getReleaseDate()), movie.getReleaseDate());
    }
}
