package com.architecture.hexagonal.exemple.movies.domain.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtil {

    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public static LocalDate getFromISO(final String dateAsString) {
        return LocalDate.parse(dateAsString, formatter);
    }

}
