package com.final_project.repositories;

import com.final_project.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Booking> getBookingsByMoviePlayId(int moviePlayId) {
        String sqlString = "SELECT * FROM bookings WHERE movie_play_id =" + moviePlayId;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlString);

        List<Booking> bookings = new ArrayList<>();

        while(rs.next()) {
            Booking booking = new Booking();

            booking.setId(rs.getInt("booking_id"));
            booking.setMoviePlayId(rs.getInt("movie_play_id"));
            booking.setSeatRow(rs.getInt("seat_row"));
            booking.setSeatNr(rs.getInt("seat_nr"));

            bookings.add(booking);
        }

        return bookings;
    }


    public int addBooking(Booking booking) {
        String sqlString = "INSERT INTO bookings(movie_play_id, seat_row, seat_nr) VALUES(?, ?, ?)";

        return jdbcTemplate.update(sqlString, booking.getMoviePlayId(), booking.getSeatRow(), booking.getSeatNr());
    }

}

