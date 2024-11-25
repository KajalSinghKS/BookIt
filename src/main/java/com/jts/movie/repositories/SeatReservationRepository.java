package com.jts.movie.repositories;

import com.jts.movie.model.entities.SeatReservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatReservationRepository extends JpaRepository<SeatReservation,Integer> {


    @Query("SELECT sr FROM SeatReservation sr WHERE sr.booking.id = :bookingId")
    List<SeatReservation> findAllByBookingId(@Param("bookingId") Integer bookingId);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT sr FROM SeatReservation sr WHERE sr.show.id = :showId AND sr.seatId = :seatId")
    List<SeatReservation> findAllByShowIdAndSeatIdAndLock(@Param("showId") Integer showId,
                                                           @Param("seatId") Integer seatId);


}
