package com.architecture.hexagonal.example.infra.api.mapper;

import com.architecture.hexagonal.example.common.utils.DateTimeUtil;
import com.architecture.hexagonal.example.domain.movies.model.Movie;
import com.architecture.hexagonal.example.domain.movies.model.Rate;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieMapper {

    public static Movie toDomain(final MovieDb movieDb) {
        if (movieDb == null) {
            return null;
        }
        return Movie.builder()
                .id(String.valueOf(movieDb.getId()))
                .title(movieDb.getTitle())
                .synopsys(movieDb.getOverview())
                .rate(new Rate(movieDb.getPopularity()))
                .releaseDate(DateTimeUtil.getFromISO(movieDb.getReleaseDate()))
                .build();
    }
}
