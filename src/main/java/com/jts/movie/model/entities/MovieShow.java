package com.jts.movie.model.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="movie_show")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieShow {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Time time;
    private Date date;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="theater_id")
    private Theater theater;

    @OneToMany(mappedBy="show",cascade=CascadeType.ALL)
    private List<Seat>showSeatList=new ArrayList<>();

    @OneToMany(mappedBy="show",cascade=CascadeType.ALL)
    private List<Ticket>ticketList=new ArrayList<>();


}
