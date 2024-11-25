package com.jts.movie.controller;

import com.jts.movie.request.BookingRequest;
import com.jts.movie.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Object> bookTicket(@RequestBody BookingRequest bookingRequest){
        try{
            Integer bookingId=bookingService.bookSeats(bookingRequest);
            return new ResponseEntity<>(bookingId, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
