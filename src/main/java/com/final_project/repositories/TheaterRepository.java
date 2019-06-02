package com.final_project.repositories;

import com.final_project.entities.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TheaterRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTheater(Theater theater) {
        String sqlQuery = "INSERT INTO theaters(title, seats_pr_row, number_of_rows) VALUES (?,?,?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, theater.getTheaterName());
                ps.setInt(2, theater.getSeatsPerRow());
                ps.setInt(3, theater.getNumberOfRows());

                return ps;
            }
        };

        return jdbcTemplate.update(psc);
    }

    public List<Theater> findAllTheaters(){
        String sqlQuery = "SELECT * FROM theaters";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateTheaters(rs);
    }

    public Theater findTheaterById(int id){
        String sqlQuery = "SELECT * FROM theaters WHERE theater_id = " + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateTheater(rs);
    }

    public void deleteTheater(int id) {
        String sqlQuery = "DELETE FROM theaters WHERE theater_id = ?";

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

    public void editTheater(Theater theater) {
        String sqlQuery = "UPDATE theaters SET title = ?, seats_pr_row = ?, number_of_rows = ? WHERE theater_id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, theater.getTheaterName());
                ps.setInt(2, theater.getSeatsPerRow());
                ps.setInt(3, theater.getNumberOfRows());
                ps.setInt(4, theater.getId());

                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }


    private List<Theater> generateTheaters(SqlRowSet rs) {
        List<Theater> theaters = new ArrayList<>();

        while (rs.next()) {
            Theater theater = new Theater();

            theater.setId(rs.getInt("theater_id"));
            theater.setTheaterName(rs.getString("title"));
            theater.setNumberOfRows(rs.getInt("number_of_rows"));
            theater.setSeatsPerRow(rs.getInt("seats_pr_row"));

            theaters.add(theater);
        }

        return theaters;
    }

    private Theater generateTheater(SqlRowSet rs) {
        Theater theater = new Theater();

        while (rs.next()) {
            theater.setId(rs.getInt("theater_id"));
            theater.setTheaterName(rs.getString("title"));
            theater.setNumberOfRows(rs.getInt("number_of_rows"));
            theater.setSeatsPerRow(rs.getInt("seats_pr_row"));
        }

        return theater;
    }
}


