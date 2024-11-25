package com.jts.movie.exceptions;

public class MovieAlreadyExists extends RuntimeException{
    private static final long serialVersionUID=1L;
    public MovieAlreadyExists(){
        super("Movie already exists with the same name and language");
    }


}
