package com.architecture.hexagonal.exemple.movies.domain.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class DateTimeUtil {

    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public static LocalDate getFromISO(final String dateAsString) {
        if (StringUtils.isEmpty(dateAsString)) {
            return null;
        }
        try {
            return LocalDate.parse(dateAsString, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            dateTimeParseException.printStackTrace();
            return null;
        }
    }

}
