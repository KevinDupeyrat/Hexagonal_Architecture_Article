package com.architecture.hexagonal.exemple.movies.domain.movies.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RequiredArgsConstructor
@Getter
public final class Rate {

    @Min(0)
    @Max(5)
    private final float value;
}
