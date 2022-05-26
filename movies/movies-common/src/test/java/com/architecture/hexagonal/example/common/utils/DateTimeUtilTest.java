package com.architecture.hexagonal.example.common.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("dateAsStringParameters")
    void shoud_not_get_local_date_from_ISO_string_if_bad_string(final String dateAsString) {
        // when
        final LocalDate result = DateTimeUtil.getFromISO(dateAsString);
        // then
        assertNull(result);
    }

    private static Stream<Arguments> dateAsStringParameters() {
        return Stream.of(
                Arguments.of(""),
                null,
                Arguments.of("04/12/2022")
        );
    }
}
