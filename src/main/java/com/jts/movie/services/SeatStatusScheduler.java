package com.jts.movie.services;

import com.jts.movie.enums.SeatStatus;
import com.jts.movie.model.entities.Booking;
import com.jts.movie.model.entities.SeatReservation;
import com.jts.movie.repositories.BookingRepository;

import com.jts.movie.repositories.SeatReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
public class SeatStatusScheduler {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SeatReservationRepository seatReservationRepository;

    @Transactional
    @Scheduled(cron="0 0/1 * * * *")
    public void scheduler(){
        List<Booking>pendingBookings=bookingRepository.findBookingCreatedAt("PENDING", LocalDateTime.now().minusMinutes(5));

        for(Booking pending:pendingBookings){
            updateStatus(pending);
        }

    }
    private void updateStatus(Booking booking) {
        booking.setBookingStatus("CANCELLED");
        booking.setPaymentStatus("TIME_OUT");

        List<SeatReservation>reserved= seatReservationRepository.findAllByBookingId(booking.getId());

        for(SeatReservation seat:reserved){
            seat.setStatus(SeatStatus.OPEN);
        }

        bookingRepository.save(booking);
    }

}
