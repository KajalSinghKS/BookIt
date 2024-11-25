package com.jts.movie.services;

import java.util.List;
import java.util.Optional;

import com.jts.movie.convertor.ShowConvertor;
import com.jts.movie.enums.SeatStatus;
import com.jts.movie.enums.SeatType;
import com.jts.movie.exceptions.ShowDoesNotExist;
import com.jts.movie.model.entities.*;

import com.jts.movie.exceptions.MovieDoesNotExist;

import com.jts.movie.exceptions.TheaterDoesNotExist;

import com.jts.movie.repositories.MovieRepository;
import com.jts.movie.repositories.SeatRepository;
import com.jts.movie.repositories.ShowRepository;
import com.jts.movie.repositories.TheaterRepository;
import com.jts.movie.request.SeatRequest;
import com.jts.movie.request.ShowRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    SeatRepository seatRepository;

    public String addShow(ShowRequest showRequest) {
        MovieShow show = ShowConvertor.showDTOtoShow(showRequest);

        Optional<Movie> movieOpt = movieRepository.findById(showRequest.getMovieId());

        if (movieOpt.isEmpty()) {
            throw new MovieDoesNotExist();
        }

        Optional<Theater> theaterOpt = theaterRepository.findById(showRequest.getTheaterId());

        if (theaterOpt.isEmpty()) {
            throw new TheaterDoesNotExist();
        }

        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get(); //to get the value inside optional object ->movie object

        show.setMovie(movie);
        show.setTheater(theater);

        show = showRepository.save(show);

        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show has been added successfully";
    }



    public List<Seat> getSeatList(Integer showId){
        Optional<MovieShow>showOpt=showRepository.findById(showId);

        if(showOpt.isEmpty()){
            throw new ShowDoesNotExist();
        }

        MovieShow show=showOpt.get();

        return show.getShowSeatList();
    }

    public String associateSeats(SeatRequest seatRequest) throws ShowDoesNotExist{
        Optional<MovieShow>showOpt=showRepository.findById(seatRequest.getShowId());

        if(showOpt.isEmpty()){
            throw new ShowDoesNotExist();
        }

        MovieShow show = showOpt.get();

        List<Seat>seatList=show.getShowSeatList();

        Integer noOfSeatsInRow=seatRequest.getNoOfSeatsInRow();
        Integer noOfPremiumSeats=seatRequest.getNoOfPremiumSeats();
        Integer noOfClassicSeats=seatRequest.getNoOfClassicSeats();

        char ch='A';
        int rowSeat=0;
        for(int i=0;i<noOfClassicSeats;i++){
            if(rowSeat==noOfSeatsInRow){
                ch=(char)(ch+1);
                rowSeat=0;
            }

            Seat seat=new Seat();
            seat.setSeatNo(ch+ Integer.toString(rowSeat+1));
            seat.setShow(show);
            seat.setSeatType(SeatType.CLASSIC);
            seat.setPrice(seatRequest.getPriceClassic());

            seatList.add(seat);

            rowSeat++;
        }

        for(int i=0;i<noOfPremiumSeats;i++){
            if(rowSeat==noOfSeatsInRow){
                ch=(char)(ch+1);
                rowSeat=0;
            }

            Seat seat=new Seat();
            seat.setSeatNo(ch+ Integer.toString(rowSeat+1));
            seat.setShow(show);
            seat.setSeatType(SeatType.PREMIUM);
            seat.setPrice(seatRequest.getPricePremium());

            seatList.add(seat);


            rowSeat++;
        }

        showRepository.save(show);


        return "Show seats are associated successfuly";
    }

}













