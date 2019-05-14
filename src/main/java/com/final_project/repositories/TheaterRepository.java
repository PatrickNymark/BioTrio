package com.final_project.repositories;

import com.final_project.entities.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.xml.Jdbc4SqlXmlHandler;
import org.springframework.stereotype.Repository;

@Repository
public class TheaterRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addTheater(Theater theater) {

        return jdbcTemplate.update("INSERT INTO theaters(title, seats_pr_row, total_rows) VALUES (?,?,?)", theater.getTheaterName(), theater.getSeatPerRow(), theater.getRowNumber());
    }

}
