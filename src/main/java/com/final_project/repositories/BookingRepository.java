package com.final_project.repositories;

import com.final_project.entities.Booking;
import com.final_project.entities.MoviePlay;
import com.final_project.entities.Ticket;
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

    @Autowired
    TicketRepository ticketRepository;


    public List<Booking> getAllBookings() {
        String sqlQuery = "SELECT * FROM bookings";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateBookings(rs);
    }

    public List<Booking> getBookingsByMoviePlayId(int id) {
        String sqlQuery = "SELECT * FROM bookings WHERE movie_play_id =" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateBookings(rs);
    }

    public Booking getBookingById(int id) {
        String sqlQuery = "SELECT * FROM bookings WHERE id" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateBooking(rs);
    }

    private List<Booking> generateBookings(SqlRowSet rs) {
        List<Booking> bookings = new ArrayList<>();

        while(rs.next()) {
            Booking booking = new Booking();

            booking.setId(rs.getInt("booking_id"));
            booking.setMoviePlayId(rs.getInt("movie_play_id"));
            booking.setTotalPrice(rs.getInt("total_price"));

            // Get tickets
            List<Ticket> tickets = ticketRepository.getTicketsByBookingId(rs.getInt("booking_id"));
            booking.setTickets(tickets);

            bookings.add(booking);
        }

        return bookings;
    }

    private Booking generateBooking(SqlRowSet rs) {
        Booking booking = new Booking();

        while(rs.next()) {
            booking.setId(rs.getInt("booking_id"));
            booking.setMoviePlayId(rs.getInt("movie_play_id"));
            booking.setTotalPrice(rs.getInt("total_price"));

            // Get tickets
            List<Ticket> tickets = ticketRepository.getTicketsByBookingId(rs.getInt("booking_id"));
            booking.setTickets(tickets);

        }

        return booking;
    }
}

