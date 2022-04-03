package com.architecture.hexagonal.exemple.movies.configuration;

import com.architecture.hexagonal.exemple.movies.domain.movies.outbound.MoviesProvider;
import com.architecture.hexagonal.exemple.movies.domain.movies.service.MoviesService;
import com.architecture.hexagonal.exemple.movies.domain.movies.service.MoviesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public MoviesService moviesService(final MoviesProvider moviesProvider) {
        return new MoviesServiceImpl(moviesProvider);
    }
}
