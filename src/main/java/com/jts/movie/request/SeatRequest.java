package com.jts.movie.request;

import lombok.Data;

@Data
public class SeatRequest {
    Integer showId;
    Integer priceClassic;
    Integer pricePremium;
    Integer noOfSeatsInRow;
    Integer noOfClassicSeats;
    Integer noOfPremiumSeats;
}
