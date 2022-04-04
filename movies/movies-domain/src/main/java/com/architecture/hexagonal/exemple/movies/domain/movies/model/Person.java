package com.architecture.hexagonal.exemple.movies.domain.movies.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public final class Person {

    @NotNull
    @NotBlank
    private final String name;
    @NotNull
    @NotBlank
    private final String firstName;

    @JsonCreator
    public Person(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }
}
