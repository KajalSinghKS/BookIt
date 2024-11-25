package com.jts.movie.repositories;

import com.jts.movie.model.entities.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<MovieShow,Integer> {

}
