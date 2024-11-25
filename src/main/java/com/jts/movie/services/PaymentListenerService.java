package com.jts.movie.services;

import com.jts.movie.enums.SeatStatus;
import com.jts.movie.model.entities.Booking;

import com.jts.movie.repositories.BookingRepository;

import com.jts.movie.repositories.SeatReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service

public class PaymentListenerService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SeatReservationRepository seatReservationRepository;

    @Transactional
    public String listen(Integer bookingId){
        Optional<Booking> bookOpt=bookingRepository.findById(bookingId);

        if(bookOpt.isEmpty()){
            throw new RuntimeException("Booking does not exist");
        }

        Booking booking= bookOpt.get();

        booking.setPaymentStatus("SUCCESS");
        booking.setBookingStatus("SUCCESS");

        seatReservationRepository.findAllByBookingId(bookingId).forEach(seatReservation->seatReservation.setStatus(SeatStatus.BOOKED));
        bookingRepository.save(booking);

        return "Payment is completed";
    }

}
