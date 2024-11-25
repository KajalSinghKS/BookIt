package com.jts.movie.controller;

import com.jts.movie.services.PaymentListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentListenerController {
    @Autowired
    private PaymentListenerService paymentListenerService;

    @PostMapping("/listen/{bookingId}/")
    public ResponseEntity<String> listen(@PathVariable Integer bookingId) {
        try{
            String result= paymentListenerService.listen(bookingId);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}