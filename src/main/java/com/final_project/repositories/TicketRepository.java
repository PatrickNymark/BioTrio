package com.final_project.repositories;

import com.final_project.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Ticket> getAllTickets() {
        // SQL String
        String sqlQuery = "SELECT * FROM tickets";
        // Get row set
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);
        // Return generated ticket list
        return generateTickets(rs);
    }

    public List<Ticket> findTicketsByBookingCode(String bookingCode) {
        String sqlQuery = "SELECT * FROM tickets WHERE booking_code = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, bookingCode);

        return generateTickets(rs);
    }

    public List<Ticket> getTicketsByMoviePlayId(int id) {
        String sqlQuery = "SELECT * FROM tickets WHERE movie_play_id =" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateTickets(rs);
    }

    public void addTicket(Ticket ticket) {
        String sqlQuery = "INSERT INTO tickets(booking_code, seat_nr, seat_row, movie_play_id) VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, ticket.getBookingCode(), ticket.getSeatNr(), ticket.getSeatRow(), ticket.getMoviePlayId());
    }

    public void deleteTicket(int id) {
        String sqlQuery = "DELETE FROM tickets WHERE ticket_id = ?";

        jdbcTemplate.update(sqlQuery, id);
    }

    private List<Ticket> generateTickets(SqlRowSet rs) {
        List<Ticket> tickets = new ArrayList<>();

        while(rs.next()) {
            Ticket ticket = new Ticket();

            ticket.setId(rs.getInt("ticket_id"));
            ticket.setBookingCode(rs.getString("booking_code"));
            ticket.setSeatNr(rs.getInt("seat_nr"));
            ticket.setSeatRow(rs.getInt("seat_row"));
            ticket.setMoviePlayId(rs.getInt("movie_play_id"));

            tickets.add(ticket);
        }

        return tickets;
    }
}
