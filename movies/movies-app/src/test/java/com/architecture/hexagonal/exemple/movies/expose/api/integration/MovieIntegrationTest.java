package com.architecture.hexagonal.exemple.movies.expose.api.integration;

import com.architecture.hexagonal.exemple.movies.DemoApplication;
import com.architecture.hexagonal.exemple.movies.domain.movies.model.Movie;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DecimalFormat;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieIntegrationTest {

    @MockBean
    private TmdbMovies tmdbMovies;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void should_fetch_populars() {
        // given
        final MovieDb movieDb = new MovieDb();
        movieDb.setId(10);
        movieDb.setTitle("Interstellar");
        movieDb.setOverview("""
                In 2067, crop blights and dust storms threaten humanity's survival. Cooper, a widowed engineer and former NASA pilot turned farmer, lives with his father-in-law, Donald, his 15-year-old son, Tom, and 10-year-old daughter, Murphy "Murph". After a dust storm, patterns inexplicably appear in the dust covering Murphy's bedroom, which she thinks is the work of a ghost. Cooper deduces the patterns were caused by gravity variations and they represent geographic coordinates in binary code. Cooper follows the coordinates to a secret NASA facility headed by Professor John Brand.
                                    
                There he learns that 48 years earlier, unknown beings placed a wormhole near Saturn, opening a path to a distant galaxy with 12 potentially habitable worlds located near a black hole named Gargantua. Twelve volunteers traveled through the wormhole to survey the planets and three — Dr. Mann, Laura Miller, and Wolf Edmunds — reported positive results. Professor Brand reveals two plans to ensure humanity's survival. Plan A involves developing an anti-gravitational propulsion theory to propel settlements into space, while Plan B involves launching the Endurance spacecraft carrying 5,000 frozen human embryos to settle a habitable planet.
                """);
        movieDb.setPopularity((float) 4.8);
        movieDb.setReleaseDate("2014-10-26");
        final List<MovieDb> movieDbs = List.of(movieDb);
        final MovieResultsPage movieResultsPage = new MovieResultsPage();
        movieResultsPage.setResults(movieDbs);
        when(tmdbMovies.getPopularMovies("en", 1)).thenReturn(movieResultsPage);
        // when
        List<Movie> response = given()
                .accept("application/json")
                .contentType("application/json")
                .when()
                .get("/api/v1/movies/populars")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Movie.class);

        // then
        assertEquals("10", response.get(0).getId());
        assertEquals("Interstellar", response.get(0).getTitle());
        assertEquals("""
                In 2067, crop blights and dust storms threaten humanity's survival. Cooper, a widowed engineer and former NASA pilot turned farmer, lives with his father-in-law, Donald, his 15-year-old son, Tom, and 10-year-old daughter, Murphy "Murph". After a dust storm, patterns inexplicably appear in the dust covering Murphy's bedroom, which she thinks is the work of a ghost. Cooper deduces the patterns were caused by gravity variations and they represent geographic coordinates in binary code. Cooper follows the coordinates to a secret NASA facility headed by Professor John Brand.
                                    
                There he learns that 48 years earlier, unknown beings placed a wormhole near Saturn, opening a path to a distant galaxy with 12 potentially habitable worlds located near a black hole named Gargantua. Twelve volunteers traveled through the wormhole to survey the planets and three — Dr. Mann, Laura Miller, and Wolf Edmunds — reported positive results. Professor Brand reveals two plans to ensure humanity's survival. Plan A involves developing an anti-gravitational propulsion theory to propel settlements into space, while Plan B involves launching the Endurance spacecraft carrying 5,000 frozen human embryos to settle a habitable planet.
                """, response.get(0).getSynopsys());
        assertEquals(26, response.get(0).getReleaseDate().getDayOfMonth());
        assertEquals(10, response.get(0).getReleaseDate().getMonthValue());
        assertEquals(2014, response.get(0).getReleaseDate().getYear());
        DecimalFormat f = new DecimalFormat("##.0");
        assertEquals("4,8", f.format(response.get(0).getRate().getValue()));
    }


    @Test
    void should_fetch_upcoming() {
        // given
        final MovieDb movieDb = new MovieDb();
        movieDb.setId(10);
        movieDb.setTitle("Interstellar");
        movieDb.setOverview("""
                In 2067, crop blights and dust storms threaten humanity's survival. Cooper, a widowed engineer and former NASA pilot turned farmer, lives with his father-in-law, Donald, his 15-year-old son, Tom, and 10-year-old daughter, Murphy "Murph". After a dust storm, patterns inexplicably appear in the dust covering Murphy's bedroom, which she thinks is the work of a ghost. Cooper deduces the patterns were caused by gravity variations and they represent geographic coordinates in binary code. Cooper follows the coordinates to a secret NASA facility headed by Professor John Brand.
                                    
                There he learns that 48 years earlier, unknown beings placed a wormhole near Saturn, opening a path to a distant galaxy with 12 potentially habitable worlds located near a black hole named Gargantua. Twelve volunteers traveled through the wormhole to survey the planets and three — Dr. Mann, Laura Miller, and Wolf Edmunds — reported positive results. Professor Brand reveals two plans to ensure humanity's survival. Plan A involves developing an anti-gravitational propulsion theory to propel settlements into space, while Plan B involves launching the Endurance spacecraft carrying 5,000 frozen human embryos to settle a habitable planet.
                """);
        movieDb.setPopularity((float) 4.8);
        movieDb.setReleaseDate("2014-10-26");
        final List<MovieDb> movieDbs = List.of(movieDb);
        final MovieResultsPage movieResultsPage = new MovieResultsPage();
        movieResultsPage.setResults(movieDbs);
        when(tmdbMovies.getUpcoming("en", 1, "FR")).thenReturn(movieResultsPage);
        // when
        List<Movie> response = given()
                .accept("application/json")
                .contentType("application/json")
                .when()
                .get("/api/v1/movies/upcoming")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList(".", Movie.class);

        // then
        assertEquals("10", response.get(0).getId());
        assertEquals("Interstellar", response.get(0).getTitle());
        assertEquals("""
                In 2067, crop blights and dust storms threaten humanity's survival. Cooper, a widowed engineer and former NASA pilot turned farmer, lives with his father-in-law, Donald, his 15-year-old son, Tom, and 10-year-old daughter, Murphy "Murph". After a dust storm, patterns inexplicably appear in the dust covering Murphy's bedroom, which she thinks is the work of a ghost. Cooper deduces the patterns were caused by gravity variations and they represent geographic coordinates in binary code. Cooper follows the coordinates to a secret NASA facility headed by Professor John Brand.
                                    
                There he learns that 48 years earlier, unknown beings placed a wormhole near Saturn, opening a path to a distant galaxy with 12 potentially habitable worlds located near a black hole named Gargantua. Twelve volunteers traveled through the wormhole to survey the planets and three — Dr. Mann, Laura Miller, and Wolf Edmunds — reported positive results. Professor Brand reveals two plans to ensure humanity's survival. Plan A involves developing an anti-gravitational propulsion theory to propel settlements into space, while Plan B involves launching the Endurance spacecraft carrying 5,000 frozen human embryos to settle a habitable planet.
                """, response.get(0).getSynopsys());
        assertEquals(26, response.get(0).getReleaseDate().getDayOfMonth());
        assertEquals(10, response.get(0).getReleaseDate().getMonthValue());
        assertEquals(2014, response.get(0).getReleaseDate().getYear());
        DecimalFormat f = new DecimalFormat("##.0");
        assertEquals("4,8", f.format(response.get(0).getRate().getValue()));
    }

    @Test
    void should_fetch_by_id() {
        // given
        final MovieDb movieDb = new MovieDb();
        movieDb.setId(10);
        movieDb.setTitle("Interstellar");
        movieDb.setOverview("""
                In 2067, crop blights and dust storms threaten humanity's survival. Cooper, a widowed engineer and former NASA pilot turned farmer, lives with his father-in-law, Donald, his 15-year-old son, Tom, and 10-year-old daughter, Murphy "Murph". After a dust storm, patterns inexplicably appear in the dust covering Murphy's bedroom, which she thinks is the work of a ghost. Cooper deduces the patterns were caused by gravity variations and they represent geographic coordinates in binary code. Cooper follows the coordinates to a secret NASA facility headed by Professor John Brand.
                                    
                There he learns that 48 years earlier, unknown beings placed a wormhole near Saturn, opening a path to a distant galaxy with 12 potentially habitable worlds located near a black hole named Gargantua. Twelve volunteers traveled through the wormhole to survey the planets and three — Dr. Mann, Laura Miller, and Wolf Edmunds — reported positive results. Professor Brand reveals two plans to ensure humanity's survival. Plan A involves developing an anti-gravitational propulsion theory to propel settlements into space, while Plan B involves launching the Endurance spacecraft carrying 5,000 frozen human embryos to settle a habitable planet.
                """);
        movieDb.setPopularity((float) 4.8);
        movieDb.setReleaseDate("2014-10-26");
        when(tmdbMovies.getMovie(10, "en")).thenReturn(movieDb);
        // when
        Movie response = given()
                .log()
                .all()
                .pathParam("id", "10")
                .accept("application/json")
                .contentType("application/json")
                .when()
                .get("/api/v1/movies/{id}")
                .then()
                .extract()
                .body()
                .jsonPath()
                .getObject(".", Movie.class);

        // then
        assertEquals("10", response.getId());
        assertEquals("Interstellar", response.getTitle());
        assertEquals("""
                In 2067, crop blights and dust storms threaten humanity's survival. Cooper, a widowed engineer and former NASA pilot turned farmer, lives with his father-in-law, Donald, his 15-year-old son, Tom, and 10-year-old daughter, Murphy "Murph". After a dust storm, patterns inexplicably appear in the dust covering Murphy's bedroom, which she thinks is the work of a ghost. Cooper deduces the patterns were caused by gravity variations and they represent geographic coordinates in binary code. Cooper follows the coordinates to a secret NASA facility headed by Professor John Brand.
                                    
                There he learns that 48 years earlier, unknown beings placed a wormhole near Saturn, opening a path to a distant galaxy with 12 potentially habitable worlds located near a black hole named Gargantua. Twelve volunteers traveled through the wormhole to survey the planets and three — Dr. Mann, Laura Miller, and Wolf Edmunds — reported positive results. Professor Brand reveals two plans to ensure humanity's survival. Plan A involves developing an anti-gravitational propulsion theory to propel settlements into space, while Plan B involves launching the Endurance spacecraft carrying 5,000 frozen human embryos to settle a habitable planet.
                """, response.getSynopsys());
        assertEquals(26, response.getReleaseDate().getDayOfMonth());
        assertEquals(10, response.getReleaseDate().getMonthValue());
        assertEquals(2014, response.getReleaseDate().getYear());
        DecimalFormat f = new DecimalFormat("##.0");
        assertEquals("4,8", f.format(response.getRate().getValue()));
    }

    @Test
    void should_not_fetch_by_id_movie_not_found() {
        // given
        when(tmdbMovies.getMovie(10, "en")).thenReturn(null);
        // when
        Response response = given()
                .pathParam("id", "10")
                .accept("application/json")
                .contentType("application/json")
                .when()
                .get("/api/v1/movies/{id}");
        // then
        assertEquals(204, response.getStatusCode());
    }

    @Test
    void should_not_fetch_by_id_when_id_null() {
        // when
        Response response = given()
                .pathParam("id", " ")
                .accept("application/json")
                .contentType("application/json")
                .when()
                .get("/api/v1/movies/{id}");
        // then
        assertEquals(400, response.getStatusCode());
        assertEquals("[id] parameter is mandatory", response.print());
    }
}
