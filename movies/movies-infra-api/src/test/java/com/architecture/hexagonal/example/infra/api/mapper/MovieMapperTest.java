package com.architecture.hexagonal.example.infra.api.mapper;

import com.architecture.hexagonal.example.common.utils.DateTimeUtil;
import com.architecture.hexagonal.example.domain.movies.model.Movie;
import com.architecture.hexagonal.example.infra.api.model.MovieDBFactory;
import info.movito.themoviedbapi.model.MovieDb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovieMapperTest {

    @Test
    void should_mappe_movie() {
        // given
        final MovieDb movieDb = MovieDBFactory.MOVIEDB_POJO;
        // when
        final Movie result = MovieMapper.toDomain(movieDb);
        // then
        assertEquals(String.valueOf(movieDb.getId()), result.getId());
        assertEquals(movieDb.getTitle(), result.getTitle());
        assertEquals(movieDb.getOverview(), result.getSynopsys());
        assertEquals(movieDb.getPopularity(), result.getRate().getValue());
        Assertions.assertEquals(DateTimeUtil.getFromISO(movieDb.getReleaseDate()), result.getReleaseDate());
    }

    @Test
    void should_not_mappe_movie_null() {
        // when
        final Movie result = MovieMapper.toDomain(null);
        // then
        assertNull(result);
    }

    @Test
    void should_not_intanciate_utility_class() {
        // given
        final Class<?> cls = MovieMapper.class;
        final Constructor<?> c = cls.getDeclaredConstructors()[0];
        c.setAccessible(true);
        // when - then
        assertThrows(InvocationTargetException.class, c::newInstance);
    }
}
