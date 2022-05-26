package com.architecture.hexagonal.example.infra.api.model;

import info.movito.themoviedbapi.model.MovieDb;
import lombok.experimental.UtilityClass;


@UtilityClass
public class MovieDBFactory {

    public static MovieDb MOVIEDB_POJO = new MovieDb();

    static {
        MOVIEDB_POJO.setId(10);
        MOVIEDB_POJO.setTitle("Interstellar");
        MOVIEDB_POJO.setOverview("""
                In 2067, crop blights and dust storms threaten humanity's survival. Cooper, a widowed engineer and former NASA pilot turned farmer, lives with his father-in-law, Donald, his 15-year-old son, Tom, and 10-year-old daughter, Murphy "Murph". After a dust storm, patterns inexplicably appear in the dust covering Murphy's bedroom, which she thinks is the work of a ghost. Cooper deduces the patterns were caused by gravity variations and they represent geographic coordinates in binary code. Cooper follows the coordinates to a secret NASA facility headed by Professor John Brand.
                                    
                There he learns that 48 years earlier, unknown beings placed a wormhole near Saturn, opening a path to a distant galaxy with 12 potentially habitable worlds located near a black hole named Gargantua. Twelve volunteers traveled through the wormhole to survey the planets and three — Dr. Mann, Laura Miller, and Wolf Edmunds — reported positive results. Professor Brand reveals two plans to ensure humanity's survival. Plan A involves developing an anti-gravitational propulsion theory to propel settlements into space, while Plan B involves launching the Endurance spacecraft carrying 5,000 frozen human embryos to settle a habitable planet.
                """);
        MOVIEDB_POJO.setPopularity((float) 4.8);
        MOVIEDB_POJO.setReleaseDate("2014-10-26");
    }
}
