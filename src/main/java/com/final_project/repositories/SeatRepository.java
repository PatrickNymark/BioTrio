package com.final_project.repositories;

import com.final_project.entities.Booking;
import com.final_project.entities.MoviePlay;
import com.final_project.entities.Seat;
import com.final_project.entities.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatRepository {

    @Autowired
    MoviePlayRepository moviePlayRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public List<Seat> getSeatsByMoviePlay(int id) {
        MoviePlay moviePlay = moviePlayRepository.getMoviePlayById(id);
        List<Booking> bookings = bookingRepository.getBookingsByMoviePlayId(id);
        Theater theater = theaterRepository.findTheaterById(moviePlay.getTheaterId());

        List<Seat> theaterSeats = new ArrayList<>();

        for(int i = 0; i < theater.getNumberOfRows(); i++) {
            for(int j = 0; j < theater.getSeatsPerRow(); j++) {
                Seat seat = new Seat();

                seat.setTheaterId(moviePlay.getTheaterId());
                seat.setNr(j + 1);
                seat.setRow(i + 1);
                seat.setReserved(false);

                // Check for availability
                for(Booking booking : bookings) {
                    if(booking.() == seat.getNr() && booking.getSeatRow() == seat.getRow()) {
                        seat.setReserved(true);
                    }
                }

                theaterSeats.add(seat);
            }
        }

        return theaterSeats;
    }
}
