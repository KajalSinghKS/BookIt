package com.jts.movie.request;

import com.jts.movie.model.entities.Seat;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private Integer userId;
    private Integer showId;
    private List<Integer> seatList;
}
