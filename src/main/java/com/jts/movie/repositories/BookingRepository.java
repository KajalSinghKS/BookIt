package com.jts.movie.repositories;

import com.jts.movie.model.entities.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer>{



        @Query("SELECT b FROM Booking b WHERE b.bookingStatus = :bookingStatus AND b.createdAt <= :createdAt")
        List<Booking> findBookingCreatedAt(@Param("bookingStatus") String bookingStatus,
                                                 @Param("createdAt") LocalDateTime createdAt);


}
