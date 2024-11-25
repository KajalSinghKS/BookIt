package com.jts.movie.model.entities;

import com.jts.movie.enums.SeatType;
import com.jts.movie.enums.SeatStatus;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "seat")
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Integer price;


    @ManyToOne
    @JoinColumn(name="show_id")
    private MovieShow show;
}
