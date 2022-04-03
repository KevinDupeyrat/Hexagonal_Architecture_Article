package com.architecture.hexagonal.exemple.movies.domain.movies.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public final class Person {

    @NotNull
    @NotBlank
    private final String name;
    @NotNull
    @NotBlank
    private final String firstName;
}
