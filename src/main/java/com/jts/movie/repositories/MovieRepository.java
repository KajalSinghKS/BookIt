package com.jts.movie.repositories;

import com.jts.movie.model.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer>  //integer is primary key for movie entity
{
    Movie findByMovieName(String name);

}
