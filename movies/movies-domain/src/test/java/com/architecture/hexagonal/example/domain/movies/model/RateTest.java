package com.architecture.hexagonal.example.domain.movies.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void should_be_rate_valid() {
        // given
        Rate rate = new Rate(4);
        // when
        Set<ConstraintViolation<Rate>> violations = validator.validate(rate);
        // then
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 6})
    void should_not_be_rate_valid_reate_minus_than_zero_or_more_than_five(final int value) {
        // given
        Rate rate = new Rate(value);
        // when
        Set<ConstraintViolation<Rate>> violations = validator.validate(rate);
        // then
        assertFalse(violations.isEmpty());
    }
}
