package com.final_project.repositories;

import com.final_project.controllers.BookingController;
import com.final_project.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Ticket> findTicketsByBookingCode(String bookingCode) {
        String sqlQuery = "SELECT * FROM tickets WHERE booking_code = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, bookingCode);

        return generateTickets(rs);
    }

    public List<Ticket> findTicketsByMoviePlayId(int id) {
        String sqlQuery = "SELECT * FROM tickets WHERE movie_play_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, id);

        return generateTickets(rs);
    }

    public void addTicket(Ticket ticket) {
        String sqlQuery = "INSERT INTO tickets(booking_code, seat_nr, seat_row, movie_play_id) VALUES(?, ?, ?, ?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, ticket.getBookingCode());
                ps.setInt(2, ticket.getSeatNr());
                ps.setInt(3, ticket.getSeatRow());
                ps.setInt(4, ticket.getMoviePlayId());

                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }

    public void deleteTicket(int id) {
        String sqlQuery = "DELETE FROM tickets WHERE ticket_id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, id);

                return ps;
            }
        };

        jdbcTemplate.update(psc);
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
