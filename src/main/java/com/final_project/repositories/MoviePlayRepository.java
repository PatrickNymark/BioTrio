package com.final_project.repositories;

import com.final_project.entities.MoviePlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MoviePlayRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MoviePlay> getMoviePlayByMovieId(int movieId) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE movie_id =" + movieId;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<MoviePlay> moviePlayList = new ArrayList<>();

        while(rs.next()) {
            MoviePlay moviePlay = new MoviePlay();

            moviePlay.setId(rs.getInt("play_id"));
            moviePlay.setMovieId(rs.getInt("movie_id"));
            moviePlay.setTheaterId(rs.getInt("theater_id"));

            Timestamp ts = rs.getTimestamp("play_start");
            moviePlay.setPlayStart(ts.toLocalDateTime());

            moviePlayList.add(moviePlay);
        }

        return  moviePlayList;
    }
}
