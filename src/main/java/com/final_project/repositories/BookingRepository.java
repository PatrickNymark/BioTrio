package com.final_project.repositories;

import com.final_project.entities.Booking;
import com.final_project.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TicketRepository ticketRepository;


    /**
     * Find all bookings
     *
     * @return List(Booking)
     */
    public List<Booking> findAllBookings() {
        String sqlQuery = "SELECT * FROM bookings";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateBookings(rs);
    }

    /**
     * Find booking by code
     *
     * @param bookingCode
     * @return Booking
     */
    public Booking findBookingByBookingCode(String bookingCode) {
        String sqlQuery = "SELECT * FROM bookings WHERE booking_code = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, bookingCode);

        return generateBooking(rs);
    }

    /**
     * Find bookings by movie play
     *
     * @param moviePlayId
     * @return List(Booking)
     */
    public List<Booking> findBookingsByMoviePlayId(int moviePlayId) {
        String sqlQuery = "SELECT * FROM bookings WHERE movie_play_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, moviePlayId);

        return generateBookings(rs);
    }

    /**
     * Saves booking
     *
     * @param booking
     */
    public void addBooking(Booking booking) {
        String sqlQuery = "INSERT INTO bookings(movie_play_id, total_price, booking_code) VALUES(?, ?, ?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, booking.getMoviePlayId());
                ps.setDouble(2, booking.getTotalPrice());
                ps.setString(3, booking.getBookingCode());
                return ps;
            }
        };


        jdbcTemplate.update(psc);
    }

    /**
     * Deletes booking
     *
     * @param bookingCode
     */
    public void deleteBooking(String bookingCode) {
        String sqlQuery = "DELETE FROM bookings WHERE booking_code = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, bookingCode);
                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }

    /**
     * Generates a list of Bookings from database
     *
     * @param rs
     * @return List(Booking)
     */
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

    /**
     * Generates a Booking from database
     *
     * @param rs
     * @return
     */
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

