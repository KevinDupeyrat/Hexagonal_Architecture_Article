package com.architecture.hexagonal.exemple.movies.domain.movies.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public final class Rate {

    @Min(0)
    @Max(5)
    private final float value;

    @JsonCreator
    public Rate(float value) {
        this.value = value;
    }
}
