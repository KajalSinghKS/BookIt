package com.jts.movie.exceptions;

public class MovieDoesNotExist extends RuntimeException {
    public MovieDoesNotExist(){
        super("Movie does not exist");
    }

}
