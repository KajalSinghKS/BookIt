package com.jts.movie.convertor;

import com.jts.movie.model.entities.Theater;
import com.jts.movie.request.TheaterRequest;

public class TheaterConvertor {
    public static Theater theaterDtoToTheater(TheaterRequest theaterRequest){
        Theater theater=Theater.builder().name(theaterRequest.getName()).address(theaterRequest.getAddress()).build();

        return theater;
    }

}
