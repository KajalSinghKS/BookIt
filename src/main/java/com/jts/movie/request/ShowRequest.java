package com.jts.movie.request;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data//getters and setters
public class ShowRequest {
    private Time showTime;
    private Date showDate;
    private Integer theaterId;
    private Integer movieId;

}
