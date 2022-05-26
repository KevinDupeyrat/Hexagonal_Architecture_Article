package com.architecture.hexagonal.example.domain.movies.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class Person {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    String firstName;

    @JsonCreator
    public Person(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }
}
