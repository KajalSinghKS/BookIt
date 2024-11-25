package com.jts.movie.exceptions;

public class TheaterDoesNotExist extends RuntimeException{
    public TheaterDoesNotExist(){
        super("Theator does not exist");
    }

}
