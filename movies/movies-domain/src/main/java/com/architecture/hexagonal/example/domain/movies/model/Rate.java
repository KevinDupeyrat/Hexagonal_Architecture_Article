package com.architecture.hexagonal.example.domain.movies.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Value
public class Rate {

    @Min(0)
    @Max(5)
    float value;

    @JsonCreator
    public Rate(float value) {
        this.value = value;
    }
}
