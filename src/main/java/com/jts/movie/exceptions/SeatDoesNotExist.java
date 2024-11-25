package com.jts.movie.exceptions;

public class SeatDoesNotExist extends RuntimeException{
    public SeatDoesNotExist(){
        super("Seat Does Not Exist");
    }

}
