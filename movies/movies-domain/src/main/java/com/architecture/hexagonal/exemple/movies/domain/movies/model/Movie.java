package com.architecture.hexagonal.exemple.movies.domain.movies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @NotBlank
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String synopsys;
    @NotNull
    private Rate rate;
    @NotNull
    private LocalDate releaseDate;
    private Person director;
    private List<Person> actors;
}
