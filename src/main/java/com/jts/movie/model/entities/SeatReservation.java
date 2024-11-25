package com.jts.movie.model.entities;

import com.jts.movie.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Table(name = "Seats_reserved")
@Entity
@Getter
@Setter
public class SeatReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer seatId;

    @ManyToOne
    @JoinColumn(name="show_id")
    private MovieShow show;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;
}
