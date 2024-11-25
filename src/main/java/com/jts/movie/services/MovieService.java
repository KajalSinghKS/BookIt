package com.jts.movie.services;

import com.jts.movie.convertor.MovieConvertor;
import com.jts.movie.model.entities.Movie;
import com.jts.movie.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jts.movie.request.MovieRequest;
import com.jts.movie.exceptions.MovieAlreadyExists;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(MovieRequest movieRequest){
        Movie movieByName=movieRepository.findByMovieName(movieRequest.getMovieName());

        if(movieByName!=null && movieByName.getLanguage().equals(movieRequest.getLanguage())){
            throw new MovieAlreadyExists();
        }

        Movie movie= MovieConvertor.movieDTOtoMovie(movieRequest);

        movieRepository.save(movie);

        return "The movie has been added successfully";

    }




}
