package com.jts.movie.convertor;

import com.jts.movie.model.entities.Movie;
import com.jts.movie.request.MovieRequest;

public class MovieConvertor {
    //movie class will have a builder class
    public static Movie movieDTOtoMovie(MovieRequest movieRequest){
        Movie movie=Movie.builder().movieName(movieRequest.getMovieName()).duration(movieRequest.getDuration()).
                genre(movieRequest.getGenre()).language(movieRequest.getLanguage()).releaseDate(movieRequest.getReleaseDate())
                .rating(movieRequest.getRating()).build();

        return movie;
    }

}
