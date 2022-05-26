package com.architecture.hexagonal.example.domain.movies.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void should_be_movie_valid() {
        // given
        final Movie movie = Movie.builder()
                .id("id")
                .title("title")
                .synopsys("hello world")
                .rate(new Rate(4))
                .releaseDate(LocalDate.now())
                .build();
        // when
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        // then
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("movieParameters")
    void should_not_be_movie_valid_for_id_title_synopsys_rate_releasedate_null_or_blank(final String id,
                                                                                        final String title,
                                                                                        final String synopsys,
                                                                                        final Rate rate,
                                                                                        final LocalDate releaseDate) {
        // given
        final Movie movie = Movie.builder()
                .id(id)
                .title(title)
                .synopsys(synopsys)
                .rate(rate)
                .releaseDate(releaseDate)
                .build();
        // when
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        // then
        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> movieParameters() {
        return Stream.of(
                Arguments.of(null, "title", "synopsys", new Rate(4), LocalDate.now()),
                Arguments.of("", "title", "synopsys", new Rate(4), LocalDate.now()),
                Arguments.of("id", null, "synopsys", new Rate(4), LocalDate.now()),
                Arguments.of("id", "", "synopsys", new Rate(4), LocalDate.now()),
                Arguments.of("id", "title", null, new Rate(4), LocalDate.now()),
                Arguments.of("id", "title", "", new Rate(4), LocalDate.now()),
                Arguments.of("id", "title", "synopsys", null, LocalDate.now()),
                Arguments.of("", "title", "synopsys", new Rate(4), null)
        );
    }
}
