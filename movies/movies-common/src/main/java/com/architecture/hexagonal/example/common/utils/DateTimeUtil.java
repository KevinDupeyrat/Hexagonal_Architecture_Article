package com.architecture.hexagonal.example.common.utils;

import lombok.experimental.UtilityClass;
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
