package com.jts.movie.repositories;

import com.jts.movie.model.entities.Theater;
import com.jts.movie.services.TheaterService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,Integer> {
    Theater findByAddress(String address);
}
