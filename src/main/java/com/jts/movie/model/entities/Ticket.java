package com.jts.movie.model.entities;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="ticket")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer ticketId;
    private Integer totalTicketsPrice;
    private String bookedSeats;

    @CreationTimestamp
    private Date bookedAt;

    @ManyToOne
    @JoinColumn(name="show_id")
    private MovieShow show;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


}
