package com.jts.movie.controller;

import com.jts.movie.model.entities.Seat;
import com.jts.movie.request.SeatRequest;
import com.jts.movie.request.ShowRequest;
import com.jts.movie.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/addNew")
    public ResponseEntity<String> addShow(@RequestBody ShowRequest showRequest){
        try{
            String result=showService.addShow(showRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{showId}/getSeats")
    public ResponseEntity<?>getSeatsForShow(@PathVariable Integer showId){
        try{
            List<Seat>seatList=showService.getSeatList(showId);
            return new ResponseEntity<>(seatList, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associateSeats")
    public ResponseEntity<String> associateShowSeats(@RequestBody SeatRequest seatRequest){
        try{
            String result=showService.associateSeats(seatRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



}
