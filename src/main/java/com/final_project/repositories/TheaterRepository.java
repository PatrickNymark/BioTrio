package com.final_project.repositories;

import com.final_project.entities.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TheaterRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTheater(Theater theater) {
       String sqlQuery = "INSERT INTO theaters(title, seats_pr_row, total_rows) VALUES (?,?,?)";

        return jdbcTemplate.update(sqlQuery, theater.getTheaterName(), theater.getSeatsPerRow(), theater.getNumberOfRows());
    }

    public List<Theater> findAllTheaters(){
        String sqlQuery = "SELECT * FROM theaters";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<Theater> theaterList= new ArrayList<>();

        while (rs.next()){
            Theater theater = new Theater();
            theater.setId(rs.getInt("theater_id"));
            theater.setTheaterName(rs.getString("title"));
            theater.setNumberOfRows(rs.getInt("total_rows"));
            theater.setSeatsPerRow(rs.getInt("seats_pr_row"));

            theaterList.add(theater);
        }

        return theaterList;
    }

    public Theater findTheaterById(int id){
        String sqlQuery = "SELECT * FROM theaters WHERE theater_id = " + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        Theater theater = new Theater();

        while (rs.next()) {
            theater.setId(rs.getInt("theater_id"));
            theater.setTheaterName(rs.getString("title"));
            theater.setNumberOfRows(rs.getInt("total_rows"));
            theater.setSeatsPerRow(rs.getInt("seats_pr_row"));
        }

        return theater;
    }
}


