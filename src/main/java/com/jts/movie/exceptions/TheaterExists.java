package com.jts.movie.exceptions;

public class TheaterExists extends RuntimeException{
    public TheaterExists(){
        super("Theater already exists");
    }

}
