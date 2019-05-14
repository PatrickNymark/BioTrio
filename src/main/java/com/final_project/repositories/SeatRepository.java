package com.final_project.repositories;

import com.final_project.entities.Booking;
import com.final_project.entities.MoviePlay;
import com.final_project.entities.Seat;
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

    public List<Seat> getSeatsByMoviePlay(int id) {
        MoviePlay moviePlay = moviePlayRepository.getMoviePlayById(id);
        List<Booking> bookings = bookingRepository.getBookingsByMoviePlayId(moviePlay.getId());

        int rows = 2;
        int numbers = 5;

        List<Seat> theaterSeats = new ArrayList<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < numbers; j++) {
                Seat seat = new Seat();

                seat.setTheaterId(moviePlay.getTheaterId());
                seat.setNr(j + 1);
                seat.setRow(i + 1);
                seat.setReserved(false);

                // Check for availability
                for(Booking booking : bookings) {
                    if(booking.getSeatNr() == seat.getNr() && booking.getSeatRow() == seat.getRow()) {
                        seat.setReserved(true);
                    }
                }

                theaterSeats.add(seat);
            }
        }

        return theaterSeats;
    }
}
