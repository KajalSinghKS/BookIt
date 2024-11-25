package com.jts.movie.model.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.jts.movie.enums.Genre;
import com.jts.movie.enums.Language;

import jakarta.persistence.*;
import lombok.*;
//import lombok.Builder;


//@Builder
@Entity
@Table(name="movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String movieName;

    private Integer duration;

    @Column(scale=2)
    private Double rating;

    private Date releaseDate;

    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    @Enumerated(value=EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy="movie",cascade=CascadeType.ALL)
    private List<MovieShow>shows=new ArrayList<>();

    private Movie(Builder builder) {
        this.movieName = builder.movieName;
        this.duration = builder.duration;
        this.genre = builder.genre;
        this.language = builder.language;
        this.releaseDate = builder.releaseDate;
        this.rating = builder.rating;
    }


    public static Builder builder() {
        return new Builder();
    }

    // Static inner Builder class
    public static class Builder {
        private String movieName;
        private int duration;
        private Genre genre;
        private Language language;
        private Date releaseDate;
        private double rating;


        public Builder movieName(String movieName) {
            this.movieName = movieName;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public Builder releaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

}
