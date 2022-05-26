package com.architecture.hexagonal.example.infra.api.configuration;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TmdbConfiguration {

    @Bean
    public TmdbMovies tmdbMovies(final AppProperties appProperties) {
        return new TmdbApi(appProperties.getKey())
                .getMovies();
    }
}
