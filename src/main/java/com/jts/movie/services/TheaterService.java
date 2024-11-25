package com.jts.movie.services;

import com.jts.movie.convertor.TheaterConvertor;
import com.jts.movie.exceptions.TheaterExists;
import com.jts.movie.model.entities.Theater;
import com.jts.movie.repositories.TheaterRepository;
import com.jts.movie.request.TheaterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(TheaterRequest theaterRequest) throws TheaterExists
    {
        if(theaterRepository.findByAddress(theaterRequest.getAddress())!=null){
            throw new TheaterExists();
        }

        Theater theater= TheaterConvertor.theaterDtoToTheater(theaterRequest);

        theaterRepository.save(theater);
        return "Theater has been saved successfully";
    }

}
