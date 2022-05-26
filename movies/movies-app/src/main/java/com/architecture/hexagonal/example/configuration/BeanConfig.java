package com.architecture.hexagonal.example.configuration;

import com.architecture.hexagonal.example.domain.movies.outbound.MoviesProvider;
import com.architecture.hexagonal.example.domain.movies.service.MoviesService;
import com.architecture.hexagonal.example.domain.movies.service.MoviesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public MoviesService moviesService(final MoviesProvider moviesProvider) {
        return new MoviesServiceImpl(moviesProvider);
    }
}
