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


    public List<Booking> findAllBookings() {
        String sqlQuery = "SELECT * FROM bookings";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateBookings(rs);
    }

    public Booking findBookingByBookingCode(String bookingCode) {
        String sqlQuery = "SELECT * FROM bookings WHERE booking_code = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, bookingCode);

        return generateBooking(rs);
    }

    public void addBooking(Booking booking) {
        String sqlQuery = "INSERT INTO bookings(movie_play_id, staff_id, customer_id, total_price, booking_code) VALUES(?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, booking.getMoviePlayId(), null, null, booking.getTotalPrice(), booking.getBookingCode());
    }

    public void deleteBooking(String bookingCode) {
        String sqlQuery = "DELETE FROM bookings WHERE booking_code = ?";

        jdbcTemplate.update(sqlQuery, bookingCode);
    }

    private List<Booking> generateBookings(SqlRowSet rs) {
        List<Booking> bookings = new ArrayList<>();

        while(rs.next()) {
            Booking booking = new Booking();

            booking.setMoviePlayId(rs.getInt("movie_play_id"));
            booking.setTotalPrice(rs.getInt("total_price"));
            booking.setBookingCode(rs.getString("booking_code"));

            // Get tickets
            List<Ticket> tickets = ticketRepository.findTicketsByBookingCode(booking.getBookingCode());
            booking.setTickets(tickets);

            bookings.add(booking);
        }

        return bookings;
    }

    private Booking generateBooking(SqlRowSet rs) {
        Booking booking = new Booking();

        while(rs.next()) {
            booking.setBookingCode(rs.getString("booking_code"));
            booking.setMoviePlayId(rs.getInt("movie_play_id"));
            booking.setTotalPrice(rs.getInt("total_price"));

            // Get tickets
            List<Ticket> tickets = ticketRepository.findTicketsByBookingCode(booking.getBookingCode());
            booking.setTickets(tickets);
        }

        return booking;
    }
}

