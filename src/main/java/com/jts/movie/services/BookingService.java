package com.jts.movie.services;

import com.jts.movie.enums.SeatStatus;
import com.jts.movie.exceptions.SeatDoesNotExist;
import com.jts.movie.exceptions.ShowDoesNotExist;
import com.jts.movie.model.entities.*;
import com.jts.movie.repositories.*;
import com.jts.movie.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    SeatReservationRepository seatReservationRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Transactional
    public Integer bookSeats(BookingRequest bookingRequest){
        Optional<MovieShow> show=showRepository.findById(bookingRequest.getShowId());
        if(show.isEmpty()){
            throw new ShowDoesNotExist();
        }

        MovieShow showToBook=show.get();
        Integer showId=show.get().getId();

        List<Integer> requestedSeatIds=bookingRequest.getSeatList();
//        List<Seat> requestedSeats=bookingRequest.getSeatList();
//        List<Integer> requestedSeatIds = requestedSeats.stream().map(Seat::getId).collect(Collectors.toList());

        List<Seat> existingSeats = seatRepository.findAllById(requestedSeatIds);
        if (existingSeats.size() != requestedSeatIds.size()) {
            throw new SeatDoesNotExist();
        }


        for (Integer seatId : requestedSeatIds) {
            List<SeatReservation> seatReservations = seatReservationRepository.findAllByShowIdAndSeatIdAndLock(showId,
                    seatId);
            if(seatReservations!=null) {
                boolean isAnySeatNotAvailable = seatReservations.stream()
                        .anyMatch(seatReservation -> seatReservation.getStatus() != SeatStatus.OPEN);

                if (isAnySeatNotAvailable) {
                    throw new RuntimeException("Requested seat is not available");
                }
            }
        }


        //created booking
        Booking booking=Booking.builder().bookingStatus("PENDING").paymentStatus("PENDING").show(showToBook).build();



        List<SeatReservation> seatReservations = requestedSeatIds.stream()
                .map(seatId -> {
                    SeatReservation newSeatReservation = new SeatReservation();
                    newSeatReservation.setShow(showToBook);
                    newSeatReservation.setStatus(SeatStatus.LOCKED);
                    newSeatReservation.setBooking(booking);
                    newSeatReservation.setSeatId(seatId);
                    return newSeatReservation;
                })
                .collect(Collectors.toList());

        booking.setSeatReservations(seatReservations);
        bookingRepository.save(booking);

        return booking.getId();

    }



}
