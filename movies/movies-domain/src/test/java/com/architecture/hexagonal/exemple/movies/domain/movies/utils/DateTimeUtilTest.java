package com.architecture.hexagonal.exemple.movies.domain.movies.utils;

import com.architecture.hexagonal.exemple.movies.domain.utils.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateTimeUtilTest {

    @Test
    void shoud_get_local_date_from_ISO_string() {
        // given
        final String dateAsString = "2022-04-12";
        // when
        final LocalDate result = DateTimeUtil.getFromISO(dateAsString);
        // then
        assertEquals(LocalDate.of(2022, 4, 12), result);
    }

    @Test
    void should_get_null_if_string_null() {
        // given
        final String dateAsString = null;
        // when
        final LocalDate result = DateTimeUtil.getFromISO(dateAsString);
        // then
        assertNull(result);
    }

    @Test
    void should_get_null_if_string_empty() {
        // given
        final String dateAsString = "";
        // when
        final LocalDate result = DateTimeUtil.getFromISO(dateAsString);
        // then
        assertNull(result);
    }

    @Test
    void should_get_null_id_string_not_ISO_format() {
        // given
        final String dateAsString = "04/12/2022";
        // when
        final LocalDate result = DateTimeUtil.getFromISO(dateAsString);
        // then
        assertNull(result);
    }
}
